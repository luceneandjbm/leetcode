ArrayList解读：

public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
	/**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;//默认大小
	transient Object[] elementData; //存储元素的数组
	private int size;//元素个数
	 private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
	/**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
	//用来区别EMPTY_ELEMENTDATA，表示使用的是默认的容量大小
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
}
下面创建ArrayList并且添加元素(add)来看ArrayList是如何工作的

//无参构造  注意：这个时候elementData数组是空的,后面添加元素的时候才会创建
public ArrayList() {
	this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

public ArrayList(int initialCapacity) {
	if (initialCapacity > 0) {
		//这个时候elementData数组是真正被创建出来了
		this.elementData = new Object[initialCapacity];
	} else if (initialCapacity == 0) {
		//注意：这个时候elementData数组是空的
		this.elementData = EMPTY_ELEMENTDATA;
	} else {
		//<0的时候
		throw new IllegalArgumentException("Illegal Capacity: "+
										   initialCapacity);
	}
}

假设是使用了无参的构造函数，来看add函数


public boolean add(E e) {//往尾部添加
	//先进行容量的判断，看是否需要扩容和创建数组
	ensureCapacityInternal(size + 1);  // Increments modCount!!
	elementData[size++] = e;
	return true;
}

private void ensureCapacityInternal(int minCapacity) {
	//如果数组是空的，那么需要指定容量
	if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
		minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
	}

	ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
	modCount++;

	//当前容量大于数组大小了,那么需要扩容,显然第一次add需要扩容
	if (minCapacity - elementData.length > 0)
		grow(minCapacity);
}

//扩容函数
private void grow(int minCapacity) {
	// overflow-conscious code
	int oldCapacity = elementData.length;
	//新容量大小为原来的1.5倍
	int newCapacity = oldCapacity + (oldCapacity >> 1);
	//如果是第一次add，那么就走这个if了，因为oldCapacity=0,newCapacity=0
	//而minCapacity=10，所以设置newCapacity=minCapacity
	if (newCapacity - minCapacity < 0)
		newCapacity = minCapacity;
	//如果newCapacity过大，那么设置为最大值MAX_ARRAY_SIZE
	if (newCapacity - MAX_ARRAY_SIZE > 0)
		newCapacity = hugeCapacity(minCapacity);
	// 数据迁移
	elementData = Arrays.copyOf(elementData, newCapacity);
}

再看remove(index)方法

public E remove(int index) {
	rangeCheck(index);//越界检查，如果index>=size那么抛出异常

	modCount++;
	/*E elementData(int index) {
        return (E) elementData[index];
    }*/
	E oldValue = elementData(index);//获取指定位置的值，见上,需要转泛型
	
	//需要移动的元素数量  删除了index位置后，后面的所有元素都需要往前移动一位
	int numMoved = size - index - 1;
	if (numMoved > 0)
		System.arraycopy(elementData, index+1, elementData, index,
						 numMoved);
	//最后一位置null
	elementData[--size] = null; // clear to let GC do its work
	return oldValue;
}
可以看到删除某位置的元素时间复杂度为O(N)

最后再看remove(Object obj)

public boolean remove(Object o) {
	if (o == null) {
		for (int index = 0; index < size; index++)
			if (elementData[index] == null) {
				fastRemove(index);
				return true;
			}
	} else {
		for (int index = 0; index < size; index++)
			if (o.equals(elementData[index])) {
				fastRemove(index);
				return true;
			}
	}
	return false;
}

分为要删除的是null还是非null的情况，然后遍历找到相等的元素，注意这里是使用equals
去比较的,找到之后使用fastRemove去删除，根据index去删除哦,该方法还是跟remove(index)
是一样的，但是没有越界检查

private void fastRemove(int index) {
	modCount++;
	int numMoved = size - index - 1;
	if (numMoved > 0)
		System.arraycopy(elementData, index+1, elementData, index,
						 numMoved);
	elementData[--size] = null; // clear to let GC do its work
}

说了这么多那么自己来实现一个

/**
 * ArrayList拥有的方法
 * list.add(e);
 *  list.add(index, element);
 *	list.remove(index);
 *	list.remove(o);
 *	list.set(index, element);
 *	list.contains(o);
 *	list.get(index);
 *	list.clear();
 *	list.size();
 * @author 12706
 *
 */
public class MyArrayList<T> implements Iterable<T> {
	//默认数组容量
	private static final int DEFAULT_CAPACITY = 10;
	//元素个数
	private int theSize;
	//容器,存储元素
	private T[] theItems;
	
	public MyArrayList() {
		doClear();
	}
	
	/**
	 * 添加元素
	 */
	public void add(T item) {
		if(theSize == theItems.length) {
			//数组扩容
			ensureCapacity(theItems.length * 2 + 1);
		}
		theItems[theSize] = item;
		theSize ++;
	}
	
	/**
	 * 指定位置添加元素
	 * @param index
	 * @param item
	 */
	public void add(int index, T item) {
		if(theSize == theItems.length) {
			//数组扩容
			ensureCapacity(theItems.length * 2 + 1);
		}
		for (int i = theSize; i > index; i --) {
			theItems[i] = theItems[i-1];
		}
		theItems[index] = item;
		theSize ++;
	}
	
	/**
	 * 删除元素
	 * @param index
	 */
	public void remove(int index) {
		if (index >= theItems.length) 
			throw new ArrayIndexOutOfBoundsException();
		for (int i = index; i < theSize - 1; i ++) {
			theItems[i] = theItems[i + 1];
		}
		theSize --;
	}
	
	/**
	 * 指定位置修改元素
	 * @param index
	 * @param item
	 */
	public T set(int index, T item) {
		if (index < 0 || index >= size()) 
			throw new ArrayIndexOutOfBoundsException();
		T old = theItems[index] ;
		theItems[index] = item;
		return old;
	}
	
	/**
	 * 查找元素
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if (index < 0 || index >= size()) 
			throw new ArrayIndexOutOfBoundsException();
		return theItems[index];
	}
	
	/**
	 * 清空
	 */
	public void clear() {
		doClear();
	}
	
	/**
	 * 初始化
	 */
	private void doClear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	
	/**
	 * 修改数组
	 * @param defaultCapacity
	 */
	public void ensureCapacity(int newCapacity) {
		if(newCapacity < theSize) 
			return;
		//数组的复制
		T[] old = theItems;
		theItems = (T[]) new Object[newCapacity];
		for (int i = 0; i < theSize; i ++) {
			theItems[i] = old[i];
		}
	}

	/**
	 * 数组元素个数
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * 判空
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T>{

		private int current = 0;
		
		@Override
		public boolean hasNext() {
			return current < theSize;
		}

		@Override
		public T next() {	
			if (! hasNext())
				throw new NoSuchElementException();
			return theItems[current ++];
		}
		
		public void remove() {
			//因为每次调用next()指针会往后移动一位，所以用--current。
			MyArrayList.this.remove(--current);
		}
	}
	public static void main(String[] args) {
		MyArrayList<Integer> list = new MyArrayList<>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(12);
		list.set(4, 99);
		list.remove(0);
		System.out.println("元素个数:" + list.size());
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			Integer in = it.next();
			System.out.println(in);
		}
		Integer in1 = list.get(0);
		System.out.println("下标0位置元素:" + in1);
	}
}
元素个数:4
3
5
6
99
下标0位置元素:3
