红黑树插入
public V put(K key, V value) {
	Entry<K,V> t = root;
	if (t == null) {
		compare(key, key); // type (and possibly null) check

		root = new Entry<>(key, value, null);
		size = 1;
		modCount++;
		return null;
	}
	int cmp;
	Entry<K,V> parent;
	// split comparator and comparable paths
	Comparator<? super K> cpr = comparator;
	if (cpr != null) {//使用自带comparator的构造方法
		do {
			parent = t;
			cmp = cpr.compare(key, t.key);
			if (cmp < 0)
				t = t.left;
			else if (cmp > 0)
				t = t.right;
			else
				return t.setValue(value);
		} while (t != null);
	}
	else {//如果没有使用带comparator的构造方法那么会进入这里
		if (key == null)
			throw new NullPointerException();
		@SuppressWarnings("unchecked")//比较规则根据key事先的compareTo方法
			Comparable<? super K> k = (Comparable<? super K>) key;
		do {//和BST的插入一样，但是没有使用递归而是使用循环
			parent = t;
			cmp = k.compareTo(t.key);
			if (cmp < 0)
				t = t.left;
			else if (cmp > 0)
				t = t.right;
			else
				return t.setValue(value);
		} while (t != null);
	}
	Entry<K,V> e = new Entry<>(key, value, parent);
	if (cmp < 0)
		parent.left = e;
	else
		parent.right = e;
	fixAfterInsertion(e);
	size++;
	modCount++;
	return null;
}
前面的操作跟BST是一样的，插入后进行调整
红黑树的5性质：
1、节点要么是红色要么是黑色
2、根节点是黑色
3、叶子节点都是黑色
4、红色节点的孩子节点都是黑色（也就是说不存在连续的红色）
5、某节点都所有的叶子节点的黑色节点数一样（简称黑高）

private void fixAfterInsertion(Entry<K,V> x) {
	//插入的节点必然是红色，否则必然违反性质5
	x.color = RED;
	//如果是null或者是根节点或者是父节点颜色 是BLACK，不做调整
	while (x != null && x != root && x.parent.color == RED) {
		//父节点是左孩子的情况 能知道   父节点是红色->爷爷节点是黑色的
		if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
			Entry<K,V> y = rightOf(parentOf(parentOf(x)));//获取叔叔节点
			if (colorOf(y) == RED) {//case1:叔叔节点是红色,x可以是左或者右节点
				setColor(parentOf(x), BLACK);//父亲  染黑
				setColor(y, BLACK);//叔叔染黑
				setColor(parentOf(parentOf(x)), RED);//爷爷染红
				x = parentOf(parentOf(x));//指针指向爷爷  回溯了
			} else {
				//case2:叔叔节点是黑色且   x是父节点的右节点  -->转case3
				if (x == rightOf(parentOf(x))) {
					x = parentOf(x);//x指向父节点p
					rotateLeft(x);//进行左旋操作
				}
				//case3：叔叔节点是黑色且  x是父节点的左节点
				setColor(parentOf(x), BLACK);//父亲染黑
				setColor(parentOf(parentOf(x)), RED);//爷爷染红
				rotateRight(parentOf(parentOf(x)));//在爷爷节点处右旋
			}
		} else {//父节点是右孩子的情况，与上面对称（旋转）
			Entry<K,V> y = leftOf(parentOf(parentOf(x)));
			if (colorOf(y) == RED) {
				setColor(parentOf(x), BLACK);
				setColor(y, BLACK);
				setColor(parentOf(parentOf(x)), RED);
				x = parentOf(parentOf(x));
			} else {
				if (x == leftOf(parentOf(x))) {
					x = parentOf(x);
					rotateRight(x);
				}
				setColor(parentOf(x), BLACK);
				setColor(parentOf(parentOf(x)), RED);
				rotateLeft(parentOf(parentOf(x)));
			}
		}
	}
	//最后父节点必须是黑色
	root.color = BLACK;
}
//对照着图来旋转
private void rotateLeft(Entry<K,V> p) {
	if (p != null) {
		Entry<K,V> r = p.right;
		p.right = r.left;
		if (r.left != null)
			r.left.parent = p;
		r.parent = p.parent;
		if (p.parent == null)
			root = r;
		else if (p.parent.left == p)
			p.parent.left = r;
		else
			p.parent.right = r;
		r.left = p;
		p.parent = r;
	}
}

/** From CLR */
private void rotateRight(Entry<K,V> p) {
	if (p != null) {
		Entry<K,V> l = p.left;
		p.left = l.right;
		if (l.right != null) l.right.parent = p;
		l.parent = p.parent;
		if (p.parent == null)
			root = l;
		else if (p.parent.right == p)
			p.parent.right = l;
		else p.parent.left = l;
		l.right = p;
		p.parent = l;
	}
}

============================================
红黑树的删除
public V remove(Object key) {
	Entry<K,V> p = getEntry(key);
	if (p == null)
		return null;

	V oldValue = p.value;
	deleteEntry(p);
	return oldValue;
}



private void deleteEntry(Entry<K,V> p) {
        modCount++;
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {//有两个孩子的时候，将p指向右子树的最小节点
            Entry<K,V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        Entry<K,V> replacement = (p.left != null ? p.left : p.right);
		//要删除的节点只有一个孩子，那么这个孩子就来替代该节点了
        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)//先调整指向替代的节点，接下来再删除p节点
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.parent = null;

            // Fix replacement
            if (p.color == BLACK)//先删除再调整   这个对应视频上的图
                fixAfterDeletion(replacement);
        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)//先调整再删除
                fixAfterDeletion(p);

            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
			/*对于上面的情况，由于是叶子节点，不管怎么旋转也没有转到中间节点，所以第二个if可以在
			最后对该节点进行删除*/

        }
    }
//下面的跟BST一样
static <K,V> TreeMap.Entry<K,V> successor(Entry<K,V> t) {
	if (t == null)
		return null;
	else if (t.right != null) {
		Entry<K,V> p = t.right;
		while (p.left != null)
			p = p.left;
		return p;
	} else {
		Entry<K,V> p = t.parent;
		Entry<K,V> ch = t;
		while (p != null && ch == p.right) {
			ch = p;
			p = p.parent;
		}
		return p;
	}
}
/** From CLR */
private void fixAfterDeletion(Entry<K,V> x) {
	//如果最开始就是红色，或者几遍循环后是红色，或者x指向了根节点那么就设置为黑色
	//方法最后一句设置了。所以x只能是黑色的，由于删除了所以导致了x这边的黑高减一 
	while (x != root && colorOf(x) == BLACK) {
		//x是父节点的左节点
		if (x == leftOf(parentOf(x))) {
			Entry<K,V> sib = rightOf(parentOf(x));//获取兄弟节点
			//case1 兄弟节点是RED   
			if (colorOf(sib) == RED) {
				setColor(sib, BLACK);//case1 兄弟节点设置为BLACK
				setColor(parentOf(x), RED);//case1 父亲节点设为RED
				rotateLeft(parentOf(x));//case1 左旋
				sib = rightOf(parentOf(x));//case1 兄弟指针指向右儿子
			}//case1得到的结果不满足性质5  会转为以下的case2,3,4
			
			//case2  兄弟是BLACK（通过case1如果是红也已经染黑了） 
			//同时兄弟节点左右孩子是BLACK
			if (colorOf(leftOf(sib))  == BLACK &&
				colorOf(rightOf(sib)) == BLACK) {
				setColor(sib, RED);//兄弟染红,父亲的黑高-1，需要继续调整
				x = parentOf(x);//x指向父节点p
			} else {
				//case3 左孩子是RED,右孩子是BLACK
				if (colorOf(rightOf(sib)) == BLACK) {
					setColor(leftOf(sib), BLACK);//兄弟的左孩子染黑
					setColor(sib, RED);//兄弟染红
					rotateRight(sib);//右旋
					sib = rightOf(parentOf(x));//兄弟节点指针指向了左孩子
				}//此时x节点的黑高仍然比兄弟节点的左节点的黑高小，继续调整转为case4
				//case4   右孩子是RED,左孩子任意
				setColor(sib, colorOf(parentOf(x)));//兄弟节点染成父节点颜色
				setColor(parentOf(x), BLACK);//父节点染黑
				setColor(rightOf(sib), BLACK);//兄弟的右孩子染黑
				rotateLeft(parentOf(x));//父节点左旋
				x = root;//到这里调整后是满足条件的，x指向根节点，准备结束了
			}
		} else { // symmetric
			Entry<K,V> sib = leftOf(parentOf(x));

			if (colorOf(sib) == RED) {
				setColor(sib, BLACK);
				setColor(parentOf(x), RED);
				rotateRight(parentOf(x));
				sib = leftOf(parentOf(x));
			}

			if (colorOf(rightOf(sib)) == BLACK &&
				colorOf(leftOf(sib)) == BLACK) {
				setColor(sib, RED);
				x = parentOf(x);
			} else {
				if (colorOf(leftOf(sib)) == BLACK) {
					setColor(rightOf(sib), BLACK);
					setColor(sib, RED);
					rotateLeft(sib);
					sib = leftOf(parentOf(x));
				}
				setColor(sib, colorOf(parentOf(x)));
				setColor(parentOf(x), BLACK);
				setColor(leftOf(sib), BLACK);
				rotateRight(parentOf(x));
				x = root;
			}
		}
	}
	setColor(x, BLACK);
}


