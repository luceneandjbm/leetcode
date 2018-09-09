public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    transient int size = 0;//元素个数
	transient Node<E> first;//第一次add的时候会指向第一次add的元素
	transient Node<E> last;//第一次add的时候会指向第一次add的元素，后续add会指向后续add的元素
	private static class Node<E> {
        E item;//存储的元素
        Node<E> next;//指向前一个节点
        Node<E> prev;//指向后一个节点

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
下面来看下主要的方法 add(),remove(index)（remove()是移除最后一个元素）

public boolean add(E e) {
	linkLast(e);
	return true;
}

/**
 * Links e as last element.
 */
void linkLast(E e) {
	final Node<E> l = last;
	//新节点的pre指向last,next指向null
	final Node<E> newNode = new Node<>(l, e, null);
	//尾节点指向新节点
	last = newNode;
	//如果是第一次添加那么头节点也指向新节点
	if (l == null)
		first = newNode;
	else
		//后续添加将l指向新节点
		l.next = newNode;
	size++;
	modCount++;
}

再来看remove(index)方法
public E remove(int index) {
	checkElementIndex(index);
	return unlink(node(index));
}

//找到index对应的节点
Node<E> node(int index) {
	// assert isElementIndex(index);

	if (index < (size >> 1)) {//节点在链表前半段
		Node<E> x = first;//从头结点开始往后一直走到index位置
		for (int i = 0; i < index; i++)
			x = x.next;
		return x;
	} else {//链表在后半段
		Node<E> x = last;
		//从尾节点开始走，走到index位置
		for (int i = size - 1; i > index; i--)
			x = x.prev;
		return x;
	}
}

/**
 * 断开链表中的x节点
 */
E unlink(Node<E> x) {
	// assert x != null;
	final E element = x.item;
	final Node<E> next = x.next;//获取next节点
	final Node<E> prev = x.prev;//获取prev节点

	//如果x是头结点，那么更新头结点
	if (prev == null) {
		first = next;
	} else {//如果x不是头结点,那么prev与next进行连接
		prev.next = next;
		x.prev = null;//断掉，我们常常忽视这个
	}
	//如果x是尾节点，那么更新尾节点位置
	if (next == null) {
		last = prev;
	} else {//如果x不是尾节点，那么prev与next进行连接
		next.prev = prev;
		x.next = null;//断掉，我们常常忽视这个
	}

	x.item = null;//清除，我们常常忽视这个
	size--;
	modCount++;
	return element;
}

可以看到remove(index)方法的时间复杂度是O(N)的，但是LinkedList的首尾操作是O(1)的
所以在首尾操作的话我们选择它，其他情况选择ArrayList。

下面来看看LinkedList提供的首尾操作api

//首部操作
list.addFirst(1);
list.offerFirst(1);
list.removeFirst();
list.poll();//队列用
list.peek();//队列用
list.peekFirst();

依次是首部添加、删除、查询
1、添加
public void addFirst(E e) {
	linkFirst(e);
}
public boolean offerFirst(E e) {
	addFirst(e);
	return true;
}
发现这两个方法除了返回之外是一样的,来看这个链接方法，很简单的
private void linkFirst(E e) {
	final Node<E> f = first;//获取头节点
	//创建新节点，next指向头节点，pre指向null
	final Node<E> newNode = new Node<>(null, e, f);
	//移动头节点位置
	first = newNode;
	if (f == null)
		last = newNode;//只有一个节点的时候，首尾节点都指向新节点
	else
		f.prev = newNode;//否则进行连接
	size++;
	modCount++;
}

2、删除
public E removeFirst() {
	final Node<E> f = first;
	if (f == null)
		throw new NoSuchElementException();
	return unlinkFirst(f);
}

public E poll() {
	final Node<E> f = first;
	return (f == null) ? null : unlinkFirst(f);
}

可以看到这两个方法也是差不多的，只是前者在链表为空的时候会抛出异常，后者返回null
private E unlinkFirst(Node<E> f) {
	// assert f == first && f != null;
	final E element = f.item;
	final Node<E> next = f.next;
	f.item = null;
	f.next = null; // help GC
	first = next;
	if (next == null)
		last = null;
	else
		next.prev = null;
	size--;
	modCount++;
	return element;
}

3、查询
public E peek() {
	final Node<E> f = first;
	return (f == null) ? null : f.item;
}
public E peekFirst() {
	final Node<E> f = first;
	return (f == null) ? null : f.item;
}
完全一样。

关于尾部操作就不说了，链表的操作自己完全能写的出来，只是效率也许比不上jdk的大神

补充：我们创建队列或者双端队列Deque的时候都是使用了LinkedList
Deque是继承了Queue接口的，提供了首尾操作的方法。


说了那么多，那么自己来实现一个链表和队列吧
/**
 * LinkedList拥有的常用方法
 * boolean add(Object obj)
 * void add(int index,Object obj)
 * Object remove(Object obj);//区别于ArrayList的remove(int index);
 * 但在下面还是写成remove(int index);
 * Object set(int index, Object obj);
 * Object get(int index);
 * isEmpty();
 * clear();
 * size();
 * @author 12706
 *
 */
public class MyLinkedList<T> implements Iterable<T>{
	
	//元素个数
	private int theSize;
	//对链表修改的次数
	public int modCount;
	//头结点
	private Node<T> beginMarker;
	
	//尾节点
	private Node<T> endMarker;
	
	private static class Node<T> {
		//节点保存的元素
		public T item;
		//节点指向的上一个引用
		public Node<T> prev;
		//节点指向的下一个引用
		public Node<T> next;
		
		public Node(T item, Node<T> prev, Node<T> next){
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	
	/**
	 * 构造方法，进行初始化
	 */
	public MyLinkedList() {
		doClear();
	}
	/**
	 * 清空
	 */
	public void clear() {
		doClear();
	}
	/**
	 * 初始化链表，收尾节点，修改量，元素个数
	 */
	private void doClear(){
		this.beginMarker = new Node<T>(null, null, null);
		this.endMarker = new Node<T>(null, beginMarker, null);
		this.beginMarker.next = this.endMarker;
		theSize = 0;
		modCount ++;
	}
	/**
	 * @return 返回元素个数
	 */
	public int size(){
		return theSize;
	}
	/**
	 * 判空
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * 尾节点添加元素
	 * @param item
	 * @return
	 */
	public boolean add(T item){
		add(size(),item);
		return true;
	}
	/**
	 * 指定位置插入元素
	 * @param index
	 * @param item
	 */
	public void add(int index, T item) {
		Node<T> node = getNode(index);
		addBefore(node,item);
	}
	private void addBefore(Node<T> old, T item) {
		Node<T> newNode = new Node<T>(item, old.prev, old);
		old.prev.next = newNode;
		old.prev = newNode;
		theSize ++;
		modCount ++;
	}
	/**
	 * 获取指定位置的节点 
	 * @param index
	 * @return
	 */
	private Node<T> getNode(int index) {
		
		if(index < 0 || index > size())
			throw new ArrayIndexOutOfBoundsException();
		Node<T> node ;
		if(index < size() / 2) {
			//从头部开始找
			node = beginMarker.next;
			for (int i = 0; i < index; i ++) {
				node = node.next;
			}
		}else {
			//从尾部开始找
			node = endMarker;
			for (int i = size(); i > index ;i --){
				node = node.prev;
			}
		}
		return node;
		
	}
	/**
	 * 指定位置删除元素
	 * @param idx
	 * @return
	 */
	public T remove(int index) {
		return remove(getNode(index));
	}
	private T remove(Node<T> old){
		old.prev.next = old.next;
		old.next.prev = old.prev;
		theSize --;
		modCount ++;
		return old.item;
	}
	/**
	 * 修改指定位置元素
	 * @param idx 位置
	 * @param item 修改的新值
	 * @return 被修改的旧值
	 */
	public T set(int index, T item) {
		Node<T> node = getNode(index);
		T oldData = node.item;
		node.item = item;
		return oldData;
	}
	/**
	 * 获取指定位置的元素
	 * @param idx
	 * @return 返回该位置的值
	 */
	public T get(int index) {
		return getNode(index).item;
	}
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator<T>{

		private Node<T> current = beginMarker.next;
		private int expModCount = modCount;
		
		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public T next() {
			if (modCount != expModCount)
				throw new ConcurrentModificationException();
			T item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			if (modCount != expModCount)
				throw new ConcurrentModificationException();
			//因为每次调用current已经往后移动了一位
			MyLinkedList.this.remove(current.prev);
			expModCount ++;
		}
	}
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		list.add(19);
		System.out.println("元素个数:"+list.size());
		System.out.println("下标2的位置元素值:" + list.get(2));
		list.add(2, 99);
		System.out.println("插入后下标2的位置元素值:" + list.get(2));
		list.set(2, 100);
		System.out.println("修改后下标2的位置元素值:" + list.get(2));
		Integer in2 = list.remove(2);
		System.out.println("删除下标2的位置元素值:" + in2);
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			Integer a = it.next();
			System.out.println(a);
		}
//		元素个数:5
//		下标2的位置元素值:4
//		插入后下标2的位置元素值:99
//		修改后下标2的位置元素值:100
//		删除下标2的位置元素值:100
//		1
//		3
//		4
//		6
//		19
	}
}

队列的数组实现
/**
 * 队列的数组实现
 * @author 12706
 *
 */
public class QueueOfArray<T> implements Iterable<T>{

	private T[] items;//存储队列元素
	
	private int theSize;//队列中元素个数
	
	private int first;//头元素下标
	
	private int last;//尾元素 下标
	
	public QueueOfArray() {
		items = (T[]) new Object[4];
		theSize = 0;
		first = 0;
		last = 0;
	}
	
	/**
	 * 数组动态扩容
	 * @param capacity 新数组容量
	 */
	public void ensureCapacity(int capacity) {
		if(capacity <= items.length)
			return;
		T[] newItems = items;
		items = (T[]) new Object[capacity];
		for(int i = 0; i < theSize; i++) {
			items[i] = newItems[(i + first) % newItems.length];
		}
		first = 0;
		last = theSize;
	}
	/**
	 * 判空
	 * @return
	 */
	public boolean isEmpty() {
		return theSize == 0;
	}
	/**
	 * @return 队列中元素个数
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * 入队
	 */
	public void enqueue(T item){
		if(theSize == items.length)
			ensureCapacity(theSize * 2 + 1);
		items[last ++] = item;
		if(last == items.length)
			last = 0;
		theSize ++;
	}
	
	/**
	 * 出队
	 * @return 队头元素
	 */
	public T dequeue() {
		if(isEmpty())
			throw new NoSuchElementException();
		T item = items[first];
		items[first ++] = null;
		if(first == items.length)
			first = 0;
		theSize --;
		return item;
	}
	/**
	 * 查看队头元素
	 * @return 队头元素
	 */
	public T peek() {
		if(isEmpty())
			throw new NoSuchElementException();
		return items[first];
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<T> {

		private int i;
		
		@Override
		public boolean hasNext() {
			return i < theSize;
		}

		@Override
		public T next() {
			if(! hasNext())
				throw new NoSuchElementException();
			T item = items[(i + first) % items.length];
			i ++;
			return item;
		}
	}
	public static void main(String[] args) {
		QueueOfArray<Integer> queue = new QueueOfArray<>();
		queue.enqueue(3);
		queue.enqueue(30);
		queue.enqueue(300);
		queue.enqueue(3000);
		queue.enqueue(30000);
		System.out.println(queue.isEmpty());
		Integer itm = queue.dequeue();
		System.out.println("出队元素:" + itm);
		System.out.println("元素个数:" + queue.size());
		Iterator<Integer> it = queue.iterator();
		while(it.hasNext()) {
			Integer item = it.next();
			System.out.println(item);
		}
//		输出
//		false
//		出队元素:3
//		元素个数:4
//		30
//		300
//		3000
//		30000
	}
}

队列的单链表实现
/**
 * 单链表实现队列
 * @author 12706
 *
 */
public class Queue<T> implements Iterable<T>{

	private Node<T> first;//头节点
	
	private Node<T> last;//尾节点
	
	private int theSize;//队列中元素个数
	
	private class Node<T> {
		private T data;//节点中保存的元素
		private Node<T> next;//指向队列的下一个节点
	}
	
	/**
	 * 构造方法对队列进行初始化
	 */
	public Queue() {
		first = null;
		last = null;
		theSize = 0;
	}
	
	/**
	 * @return 队列是否为空
	 */
	public boolean isEmpty() {
		return theSize == 0;
	}
	/**
	 * @return 队列中元素个数
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * 入队
	 */
	public void enqueue(T item) {
		Node<T> oldlast = last;
		last = new Node<>();
		last.data = item;
		last.next = null;
		if(isEmpty()) 
			//如果是空队列，first和last指向同一节点
			first = last;
		else
			oldlast.next = last;
		theSize ++;
	}
	
	/**
	 * 出队
	 * @return 队头元素
	 */
	public T dequeue() {
		if(isEmpty())
			throw new NoSuchElementException();
		Node<T> oldFirst =first;
		first = first.next;
		theSize --;
		if(isEmpty())
			//如果队列空了，重置last节点
			last = null;
		return oldFirst.data;
	}
	
	/**
	 * @return 队头元素
	 */
	public T peek() {
		if(isEmpty())
			throw new NoSuchElementException();
		return first.data;
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueIterator<>(first);
	}
	
	private class QueueIterator<T> implements Iterator<T> {

		private Node<T> current;
		
		public QueueIterator(Node<T> first) {
			this.current = first;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}
		@Override
		public T next() {
			if(! hasNext())
				throw new NoSuchElementException();
			T item = current.data;
			current = current.next;
			return item;
		}
	}
	public static void main(String[] args) {
		Queue<String> queue = new Queue<>();
		queue.enqueue("a");
		queue.enqueue("aa");
		queue.enqueue("aaa");
		queue.enqueue("aaaa");
		queue.enqueue("aaaaa");
		System.out.println(queue.isEmpty());
		String itm = queue.dequeue();
		System.out.println("出队元素:" + itm);
		System.out.println("队列元素个数:" + queue.size());
		Iterator<String> it = queue.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
	}
//	输出：
//	false
//	出队元素:a
//	队列元素个数:4
//	aa
//	aaa
//	aaaa
//	aaaaa

	
}
