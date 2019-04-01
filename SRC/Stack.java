Stack解读：
说到栈就三个方法
push(T t)
pop();
peek();

看源码吧，很简单
public class Stack<E> extends Vector<E> {
	//创建空的栈
	public Stack() {
    }
	
	//压栈
	public E push(E item) {
		//在数组末尾添加元素
        addElement(item);
        return item;
    }
	
	public synchronized E pop() {
        E obj;
        int len = size();
        obj = peek();//获取数组末尾元素
        removeElementAt(len - 1);//删除数组末尾元素
        return obj;
    }
	
	public synchronized E peek() {
        int len = size();
        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);//查询数组最后一个位置元素
    }
	
}

注意看，这三个方法都是线程安全的
添加元素方法 addElement(E item)  来自Vector
public synchronized void addElement(E obj) {
	modCount++;
	ensureCapacityHelper(elementCount + 1);//容量检查，和ArrayList一样
	elementData[elementCount++] = obj;//数组末尾添加元素
}

所以入栈就是在数组末尾添加元素(Vector内是维护了一个Object数组的)

在看elementAt，查询某个位置的元素,这peek种是查询末尾
public synchronized E elementAt(int index) {
	if (index >= elementCount) {
		throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
	}
	return elementData(index);
}

最后看pop方法中的removeElementAt(index)，删除index位置元素
public synchronized void removeElementAt(int index) {
	modCount++;
	if (index >= elementCount) {
		throw new ArrayIndexOutOfBoundsException(index + " >= " +
												 elementCount);
	}
	else if (index < 0) {
		throw new ArrayIndexOutOfBoundsException(index);
	}
	int j = elementCount - index - 1;
	//移动数组，这个和ArrayList是一样的，但是栈中调用的时候发现j==0，所以不会
	//移动，栈的操作是O(1)的
	if (j > 0) {
		System.arraycopy(elementData, index + 1, elementData, index, j);
	}
	elementCount--;
	//最后一个元素置为null
	elementData[elementCount] = null; /* to let gc do its work */
}

说了那么多那么自己来实现两个，一个数组实现一个链表实现
/**
 * 栈的数组实现
 * 标准库中Stack继承了Vector(线程安全的List)
 * 方法:
 * push(T t),empty(),pop(),peek(),search(Object obj)(后三者为synchonized修饰)
 * @author 12706
 *
 */
public class StackOfArray<T> implements Iterable<T> {
	private T[] items;//保存栈元素
	
	private int theSize;//栈元素个数
	
	/**
	 * 初始化方法
	 */
	public StackOfArray() {
		items = (T[])new Object[4];//默认数组大小是4
		theSize = 0;
	}
	/**
	 * 判空
	 * @return
	 */
	public boolean isEmpty() {
		return theSize == 0;
	}
	/**
	 * @return  返回栈元素个数
	 */
	public int size() {
		return theSize;
	}
	/**
	 * 动态扩容
	 */
	public void ensureCapacity(int newSize) {
		if(newSize <= theSize)
			return;
		T[] oldItems = items;
		items = (T[]) new Object[newSize];
		for(int i = 0; i < theSize; i++) {
			items[i] = oldItems[i];
		}
	}
	/**
	 * 压栈
	 */
	public void push(T item) {
		if(theSize == items.length)
			ensureCapacity(theSize * 2 +1);
		items[theSize ++] = item;
	}
	/**
	 * 弹栈
	 * @return 返回栈顶元素
	 */
	public T pop() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		T oldItem = items[-- theSize];
		items[theSize] = null;
		return oldItem;
	}
	/**
	 * @return 得到栈顶元素
	 */
	public T peek() {
		if(isEmpty()) 
			//throw new NoSuchElementException();
			return null;//实际应抛出异常，返回null是为了测试方便
		T oldItem = items[theSize - 1];
		return oldItem;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyStackIterator();
	}
	private class MyStackIterator implements Iterator<T>{

		private int current = theSize - 1;//保存栈顶元素下标
		
		@Override
		public boolean hasNext() {
			return current >= 0;
		}

		@Override
		public T next() {
			if(! hasNext())
				throw new NoSuchElementException();
			return items[current--];
		}
		
	}
	public static void main(String[] args) {
		StackOfArray<Integer> stack = new StackOfArray<>();
		stack.push(2);
		stack.push(3);
		stack.push(5);
		stack.push(6);
		stack.push(19);
		stack.push(13);
		System.out.println(stack.isEmpty());
		System.out.println("元素个数:" + stack.size());
//		while(stack.peek() != null) {
//			Integer item = stack.pop();
//			System.out.println(item);
//		}
		Iterator<Integer> it = stack.iterator();
		while(it.hasNext()) {
			Integer item = it.next();
			System.out.println(item);
		}
	}
//	输出
//	false
//	元素个数:6
//	13
//	19
//	6
//	5
//	3
//	2
}

/**
 * 使用链表实现自己的栈
 * @author 12706
 *
 */
public class Stack<T> implements Iterable<T>{
	
	private Node<T> head;//头结点，也就是栈顶元素
	
	private int theSize;//栈中元素个数
	/**
	 *节点：
	 *1、包含了元素data，
	 *2、指向下一个节点的引用next
	 * @param <T> 节点中保存的元素
	 */
	private class Node<T>{
		public T data;
		public Node<T> next;
	}
	
	/**
	 * 初始化
	 */
	public Stack() {
		head = null;
		theSize = 0;
	}
	
	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return theSize == 0;
	}
	/**
	 * 返回栈中元素个数
	 * @return 栈中元素个数
	 */
	public int size() {
		return theSize;
	}
	/**
	 * 压栈
	 * 主要是更新头结点head
	 * @param item 添加的元素
	 */
	public void push(T item) {
		Node<T> oldHead = head;
		head = new Node<>();
		head.data = item;
		head.next = oldHead;
		theSize ++;
		
	}
	/**
	 * 弹栈
	 * @return 栈顶元素
	 */
	public T pop(){
		if(isEmpty()) 
			throw new NoSuchElementException();
		T t = head.data;
		head = head.next;
		theSize --;
		return t;
	}
	
	public T peek() {
		if(isEmpty()) 
			throw new NoSuchElementException();
		return head.data;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyStackIterator(head);
	}
	
	private class MyStackIterator implements Iterator<T> {

		//初始化时传入栈的栈顶引用
		private Node<T> current;
		
		public MyStackIterator(Node<T> head) {
			this.current = head;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(! hasNext()) 
				throw new NoSuchElementException();
			T data = current.data;
			current = current.next;
			return data;
		}
		
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2);
		stack.push(4);
		stack.push(56);
		stack.push(34);
		stack.push(113);
		stack.push(46);
		System.out.println(stack.isEmpty());
		System.out.println("元素个数:" + stack.size());
		Iterator<Integer> it = stack.iterator();
		while(it.hasNext()) {
			Integer data = it.next();
			System.out.println(data);
		}
	}
//	输出：
//	false
//	元素个数:6
//	46
//	113
//	34
//	56
//	4
//	2
}
