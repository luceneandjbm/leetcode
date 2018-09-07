先说下hashmap的大致思想
里面存的是个数组链表
默认长度 16
/**
 * The default initial capacity - MUST be a power of two.
 */
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 默认初始容量
/**
 * The maximum capacity, used if a higher value is implicitly specified
 * by either of the constructors with arguments.
 * MUST be a power of two <= 1<<30.
 */
static final int MAXIMUM_CAPACITY = 1 << 30;//最大容量

/**
 * The load factor used when none specified in constructor.
 */
static final float DEFAULT_LOAD_FACTOR = 0.75f;//默认装载因子
为什么使用位移运算符？因为快，为什么必须是2的n次幂？后面说
当数组使用率达到0.75的时候进行扩容

每次put一个数，计算hash值然后计算到存储到数组中的位置，如果该位置没有元素，那么直接放入
如果有元素，那么会顺着链表去比较，如果没有相同的则存储在链表中（表尾？）

static final int TREEIFY_THRESHOLD = 8;
static final int UNTREEIFY_THRESHOLD = 6;
static final int MIN_TREEIFY_CAPACITY = 64;
但是如果链表很长，最后数组可能退化成链表，查找效率就变成了O(N)，1.8之后引入了红黑树，
当链表长度达到8的时候就转换成红黑树（最坏时间复杂度是O(N)），当红黑树节点达到6的时候
又退化成链表。但是当表的大小小于64的时候仍然不会进行树化

下面是几个成员变量

transient int size;//表中元素个数
transient int modCount;//对表的修改次数
int threshold;//阈值 (capacity * load factor)
final float loadFactor;//装载因子


下面从put方法读起，读通了其他的都几乎能猜到

1、put
public V put(K key, V value) {
	return putVal(hash(key), key, value, false, true);
}

再来看hash函数

static final int hash(Object key) {
	int h;
	return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}

h = key.hashCode()会得到hashcode值h，h >>> 16无符号右移16位，右边16位全补0，此时
只剩原来的高16位了，举个例子比如得到的h=45,无符号右移16位，那么64位全是0了。
原来的h：
00000000 00000000 | 00000000 00101101 这16全部移除掉
移动后的->h1：
00000000 00000000(这16位是补齐的) | 00000000 00000000

现在h与h1异或，显然左边16位全是0，右16位是等于是h的原来高16位和低16位异或，充分用到了32位

具体的为什么这样做后面会提到，这里先提一下用处（来自并发编程的艺术）：进行再散列目的是减少散列冲突
使得，元素能够均匀分布在不同位置，从而提高存取效率。

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
	Node<K,V>[] tab; Node<K,V> p; int n, i;
	if ((tab = table) == null || (n = tab.length) == 0)
		n = (tab = resize()).length;
	if ((p = tab[i = (n - 1) & hash]) == null)
		tab[i] = newNode(hash, key, value, null);
	else {
		...
	}
	++modCount;//放入了一个元素，所以修改量++
	if (++size > threshold)//判断是否超过阈值，是的话进行扩容
		resize();
	afterNodeInsertion(evict);//该方法没有实现，是为了继承它的LinkedHashMap所准备的
	return null;
}
resize里面先看一部分会起作用的代码如下
final Node<K,V>[] resize() {
	Node<K,V>[] oldTab = table;//一般操作不会直接操作成员变量
	int oldCap = (oldTab == null) ? 0 : oldTab.length;
	int oldThr = threshold;
	int newCap, newThr = 0;
	if (oldCap > 0) {
		...
	}
	else if (oldThr > 0) // initial capacity was placed in threshold
		...
	else {               // zero initial threshold signifies using defaults
		newCap = DEFAULT_INITIAL_CAPACITY;//初始化大小
		newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);//初始化阈值
	}
	if (newThr == 0) {
		...
	}
	threshold = newThr;//设置阈值
	@SuppressWarnings({"rawtypes","unchecked"})
		Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];//初始化表
	table = newTab;//初始化表
	if (oldTab != null) {
		...
	}
	return newTab;//返回初始化后的表
}
现在resize方法返回了初始化的表了，大小是16，设置了n=16
p = tab[i = (n - 1) & hash]
这行代码来决定新添加的元素放在哪个位置，显然只能放在0-15的下标位置
源码中要求了表大小必须是2的次幂也就在这，比如16的二进制如下
00000000 00000000 00000000 00010000
2的次幂就是31个0加个1组成的二进制
然后n-1=15,接下来15二进制如下
00000000 00000000 00000000 00001111
接下来和hash值&，不管hash值的前28位是什么，与了15之后都是0，后面四位与1111去&
不管怎么&，结果最大也就是15，对应表的最大下标。

不管是16，还是32,64,128，...,2^n，道理都和16一样（这里解释了为什么&）。
那为什么不是别的数呢？举个极端情况，n=17，那么n-1=16，其中只有一位是1，那他与其他数
&的结果要么是0，要么是1，对应散列到了2个下标位置，这就很恐怖了
所以使用2的次幂能更好的进行hash，元素能够均匀分布在不同位置，从而提高存取效率。

由于是第一次put，所以该位置肯定没有值，就在该位置放入
tab[i] = newNode(hash, key, value, null);

2、接下来考虑冲突（hashmap最初的设计数组链表有就是使用链地址法来解决冲突的）
现在再put一个元素，假如跟上面put的元素计算得到的位置是一样的，那么怎么处理呢？
来看putVal里面省略的else代码块
if ((p = tab[i = (n - 1) & hash]) == null)
    tab[i] = newNode(hash, key, value, null);
else {
	Node<K,V> e; K k;
	if (p.hash == hash &&
		((k = p.key) == key || (key != null && key.equals(k))))
		e = p;
	else if (p instanceof TreeNode)
		e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
	else {
		for (int binCount = 0; ; ++binCount) {
			//添加到末尾
			if ((e = p.next) == null) {
				p.next = newNode(hash, key, value, null);
				if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
					treeifyBin(tab, hash);
				break;
			}
			//判断是否有重复key，有的话跳出循环，修改value值
			if (e.hash == hash &&
				((k = e.key) == key || (key != null && key.equals(k))))
				break;
			p = e;
		}
	}
	//这种情况要么就是该位置为空(if else)要么就是链表中存在了重复key
	//如果是添加到了链表末尾那么e==null
	if (e != null) { // existing mapping for key
		V oldValue = e.value;
		if (!onlyIfAbsent || oldValue == null)
			e.value = value;//修改value值
		afterNodeAccess(e);
		return oldValue;
	}
}
Node<K,V> e; K k;
if (p.hash == hash &&
	((k = p.key) == key || (key != null && key.equals(k))))
	e = p;

(1)、判断原来i位置的p的hash值与新添加的元素的hash，且equals比较是否相等，是的话认为key是
完全相等的，让e指向p原来指向的内存区域（个人觉得可以将p再指向null了）

接着修改e的值为新put进来的值，返回旧值. afterNodeAccess(e);也是留给LinkedHashMap的
if (e != null) { // existing mapping for key
	V oldValue = e.value;
	if (!onlyIfAbsent || oldValue == null)
		e.value = value;
	afterNodeAccess(e);
	return oldValue;
}

（2）、如果比较不相等(也只是说明该i位置形成的链表的第一个元素不同，需要遍历链表看能
否找到相同)而且else if (p instanceof TreeNode)这句也不成立
那么走 else 代码块
else {
	for (int binCount = 0; ; ++binCount) {
		if ((e = p.next) == null) {
			p.next = newNode(hash, key, value, null);
			if (binCount >= TREEIFY_THRESHOLD - 1) // 大于树化的阈值-1=7的时候进行树化
				treeifyBin(tab, hash);
			break;
		}
		if (e.hash == hash &&
			((k = e.key) == key || (key != null && key.equals(k))))//key完全一样
			break;
		p = e;//链表指针往后移动
	}
}

因为是第二个元素，如果key完全一样那么就跳出循环，在后面进行覆盖。如果遍历完了而且
元素个数<7那么设置链表尾节点为新添加的元素,此时的e是null值，那么说明没有意义的key，可以添加
到链表末尾了
if ((e = p.next) == null) {
	p.next = newNode(hash, key, value, null);
表示到找到最后一个也没找到一样的key，那么新加入的节点作为链表尾节点

下面讲链表转红黑树
	
假设已经添加了7个了，加上这个就刚好8个=转换为红黑树的阈值(循环是0开始)	
注意看这段代码：jdk1.8新加的红黑树，不过这里只是将链表转为树的容器bin（因为还没用到左右节点）
if (binCount >= TREEIFY_THRESHOLD - 1) // 大于树化的阈值-1=7的时候进行树化
	treeifyBin(tab, hash);
break;

方法和树节点定义如下：

static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
	TreeNode<K,V> parent;  // red-black tree links
	TreeNode<K,V> left;
	TreeNode<K,V> right;
	TreeNode<K,V> prev;    // needed to unlink next upon deletion
	boolean red;
	TreeNode(int hash, K key, V val, Node<K,V> next) {
		super(hash, key, val, next);
	}
	其他方法
}

在数组中每个下标处的链表  转换成红黑树(形式还是跟链表一样，只用到了pre和next，没用到左右节点left和right)
/**
 * Replaces all linked nodes in bin at index for given hash unless
 * table is too small, in which case resizes instead.
 */
final void treeifyBin(Node<K,V>[] tab, int hash) {
	int n, index; Node<K,V> e;
	if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
		resize();
	else if ((e = tab[index = (n - 1) & hash]) != null) {
		TreeNode<K,V> hd = null, tl = null;
		do {
			TreeNode<K,V> p = replacementTreeNode(e, null);
			if (tl == null)
				hd = p;
			else {
				p.prev = tl;
				tl.next = p;
			}
			tl = p;
		} while ((e = e.next) != null);
		if ((tab[index] = hd) != null)
			hd.treeify(tab);
	}
}

if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
	resize();
这个if是成立的，因为表的大小才16，树化的表最小大小是64，所以只扩容，不树化。
现在我们假设表大小大于了64，所以我们走上面的 else if 循环

这部分代码块的作用是将链表中节点Node转为树的节点TreeNode。如图链表bin转红黑树bin
其中hd表示head头节点，tl表示tail尾节点
if ((tab[index] = hd) != null)
	hd.treeify(tab);
表示了将表的index位置引用指向hd指向的内存，注意hd.treeify(tab)，这个才表示了从hd开始
将这个树bin进行树化，来看源码

/**
 * Forms tree of the nodes linked from this node.
 * @return root of tree
 */
final void treeify(Node<K,V>[] tab) {
	TreeNode<K,V> root = null;
	for (TreeNode<K,V> x = this, next; x != null; x = next) {
		next = (TreeNode<K,V>)x.next;
		x.left = x.right = null;
		if (root == null) {
			x.parent = null;
			x.red = false;
			root = x;
		}
		else {
			K k = x.key;
			int h = x.hash;
			Class<?> kc = null;
			for (TreeNode<K,V> p = root;;) {
				int dir, ph;
				K pk = p.key;
				if ((ph = p.hash) > h)
					dir = -1;
				else if (ph < h)
					dir = 1;
				else if ((kc == null &&
						  (kc = comparableClassFor(k)) == null) ||
						 (dir = compareComparables(kc, k, pk)) == 0)
					dir = tieBreakOrder(k, pk);

				TreeNode<K,V> xp = p;
				if ((p = (dir <= 0) ? p.left : p.right) == null) {
					x.parent = xp;
					if (dir <= 0)
						xp.left = x;
					else
						xp.right = x;
					root = balanceInsertion(root, x);
					break;
				}
			}
		}
	}
	moveRootToFront(tab, root);
}

这里就不一行一行读了，大致来讲操作过程
从传入的链表形式的树的bin的头节点开始，如果是头节点，那么设置parent为null，颜色为黑色
root指向该节点
if (root == null) {
	x.parent = null;
	x.red = false;//true表示红，false表示黑
	root = x;
}

接下来取bin的第二个节点，第三个节点...第n个节点插入，每次插入做两件事
第一：首先直接插入，类似于二叉查找树的节点插入（比较的大小是hash值）
BST比较的是数值（假设插入的是整数类型），而这里比较的bin中TreeNode的hash值与树中
节点的hash值，小的话往右找，大的话往左找。

第二：进行调整，使得满足红黑树性质。(这里跟TreeMap的条件几乎是一样的,经典的红黑树)

也就是给了一堆节点，来组成红黑树。以后再put/remove到了这个index位置就是执行红黑树插入
和删除操作了。

我也就没继续读下去了，关于红黑树的操作（主要是插入、删除）这在TreeMap里面得到了很好的体现


HashMap有个扩容机制需要来解读下，也就是resize方法
final Node<K,V>[] resize() {
	Node<K,V>[] oldTab = table;
	int oldCap = (oldTab == null) ? 0 : oldTab.length;
	int oldThr = threshold;
	int newCap, newThr = 0;
	if (oldCap > 0) {
		if (oldCap >= MAXIMUM_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return oldTab;
		}
		else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
				 oldCap >= DEFAULT_INITIAL_CAPACITY)
			newThr = oldThr << 1; // double threshold
	}
	else if (oldThr > 0) // initial capacity was placed in threshold
		newCap = oldThr;
	else {               // zero initial threshold signifies using defaults
		newCap = DEFAULT_INITIAL_CAPACITY;
		newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
	}
	if (newThr == 0) {
		float ft = (float)newCap * loadFactor;
		newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
				  (int)ft : Integer.MAX_VALUE);
	}
	threshold = newThr;
	@SuppressWarnings({"rawtypes","unchecked"})
		Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
	table = newTab;
	if (oldTab != null) {
		for (int j = 0; j < oldCap; ++j) {
			Node<K,V> e;
			if ((e = oldTab[j]) != null) {
				oldTab[j] = null;
				//该位置只有一个节点，注意此时放入的位置改变了
				if (e.next == null)
					newTab[e.hash & (newCap - 1)] = e;
				else if (e instanceof TreeNode)
					((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
				//如果该位置形成了链表，为了后续不那么快形成树，分高低位进行迁移
				else { // preserve order
					Node<K,V> loHead = null, loTail = null;
					Node<K,V> hiHead = null, hiTail = null;
					Node<K,V> next;
					do {
						next = e.next;
						if ((e.hash & oldCap) == 0) {
							//注意头结点不会是空
							if (loTail == null)
								loHead = e;
							else
								loTail.next = e;
							loTail = e;
						}
						else {
							if (hiTail == null)
								hiHead = e;
							else
								hiTail.next = e;
							hiTail = e;
						}
					} while ((e = next) != null);
					//注意一定要断开，否则会与高位连接在一起
					if (loTail != null) {
						loTail.next = null;
						newTab[j] = loHead;
					}
					if (hiTail != null) {
						hiTail.next = null;
						//与低位相差oldCap距离
						newTab[j + oldCap] = hiHead;
					}
				}
			}
		}
	}
	return newTab;
}
前面已经说过，表为空的执行流程，这里假设表容量在加了这个元素后刚好超过了12(阈值),那么
这里进行扩容。
if (oldCap > 0) {
	if (oldCap >= MAXIMUM_CAPACITY) {
		threshold = Integer.MAX_VALUE;
		return oldTab;
	}
	else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
			 oldCap >= DEFAULT_INITIAL_CAPACITY)
		newThr = oldThr << 1; // double threshold
}
...
threshold = newThr;
@SuppressWarnings({"rawtypes","unchecked"})
	Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
table = newTab;
容量为原来的2倍(newCap = oldCap << 1)，同时阈值也是两倍(newThr = oldThr << 1)

扩容之后将旧表的数据搬到新表
if (oldTab != null) {
	for (int j = 0; j < oldCap; ++j) {
		Node<K,V> e;
		if ((e = oldTab[j]) != null) {
			oldTab[j] = null;
			if (e.next == null)
				newTab[e.hash & (newCap - 1)] = e;
			else if (e instanceof TreeNode)
				((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
			else { // preserve order
				Node<K,V> loHead = null, loTail = null;
				Node<K,V> hiHead = null, hiTail = null;
				Node<K,V> next;
				do {
					next = e.next;
					if ((e.hash & oldCap) == 0) {//对低段的处理
						if (loTail == null)
							loHead = e;
						else
							loTail.next = e;
						loTail = e;
					}
					else {//对高段的处理
						if (hiTail == null)
							hiHead = e;
						else
							hiTail.next = e;
						hiTail = e;
					}
				} while ((e = next) != null);
				if (loTail != null) {//低段尾节点指向null，设置回新数组
					loTail.next = null;
					newTab[j] = loHead;
				}
				if (hiTail != null) {//高段尾节点指向null，设置回新数组
					hiTail.next = null;
					newTab[j + oldCap] = hiHead;
				}
			}
		}
	}
}
上面的扩容机制比较特殊，不是简单的从旧表中取出元素hash后之间插入到新表
使用分段，分为低段lo和高段hi,看下面这行分的机制
if ((e.hash & oldCap) == 0) {
由于oldCap是2的次幂，所以二进制肯定是32个0当中一个0被1替代，比如00000000000000000010000000000000
它&上任何一个值，就看这个值在oldCap的1所在位置是0还是1，结果要么是0，要么是oldCap

如果0的话形成一个链表，不是0形成另外一个链表。为了直观，见下面的图，其中的0,1表示e.hash & oldCap
之后是0和不是0.  看xxxxxxxxxxxxxxxxxxxxx图。解释在代码上也标注了。

关于jdk1.7 对于说并发说HashMap会出现死循环，问题也就出在扩容的时候，不过那块代码跟1.8不一样
现在改进了，但是仍然不是线程安全的，对于整张表，链表，树，都没做任何安全操作。

简单拿put举个例子，加入插入的两个元素,散列到了同一个位置，自然形成链表。如果a获取了链表的
最后一个节点Node(Node.next==null判断出来)，准备插入，此时另外一个线程插入b了，此时有
Node.next=b,当时间片分到了第一个线程，那么又Node.next=a,b被覆盖掉了。

关于remove方法我就是简单看了两分钟，大体就是，定位下标，找到p，分两种情况
1、如果这个下标位置就是要找的节点，赋给node
if (p.hash == hash &&
	((k = p.key) == key || (key != null && key.equals(k))))
	node = p;
2、如果不是，那么从该节点（头结点，可能是链表头节点也可能是红黑树头节点）往后找
	2.1、首先判断p是不是树节点，是的话，就调用树的getTreeNode去查找（O（logN）复杂度）
		if (p.hash == hash &&
			((k = p.key) == key || (key != null && key.equals(k))))
			node = p;
	2.2、否则，循环从链表中查找该节点node
		else {
			do {
				if (e.hash == hash &&
					((k = e.key) == key ||
					 (key != null && key.equals(k)))) {
					node = e;
					break;
				}
				p = e;
			} while ((e = e.next) != null);
		}
		
找到节点node后，先判断是不是树节点，是的话执行树的删除
if (node instanceof TreeNode)
    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
否则执行链表的删除
if (node != null && (!matchValue || (v = node.value) == value ||
					 (value != null && value.equals(v)))) {
	if (node instanceof TreeNode)
		((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);//树节点的删除
	else if (node == p)
		tab[index] = node.next;
	else
		p.next = node.next;//链表节点的删除
	++modCount;
	--size;
	afterNodeRemoval(node);
	return node;
}
过程如下见图  xxxxxxxxxxxxxxxxxxxxxx图


其实最好b的next指针应该指向null，这里是等着GC回收了。

关于红黑树的性质插入删除操作比较麻烦，会单独写出来。
