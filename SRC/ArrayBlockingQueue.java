ArrayBlockingQueue解读：

听名字就知道是数组形成的队列
那么内部肯定维护了一个Obj数组，下一个入队下标，下一个出队下标，元素个数
既然是阻塞的，那么应该是使用了Lock锁，还有Condition的

public class ArrayBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable {
	/** The queued items */
	final Object[] items;//内部数组,创建对象的时候创建

	/** items index for next take, poll, peek or remove */
	int takeIndex; //下一个出队下标

	/** items index for next put, offer, or add */
	int putIndex; //下一个入队下标

	/** Number of elements in the queue */
	int count;	//元素个数
	
	/** Main lock guarding all access */
	final ReentrantLock lock;//锁，创建对象的时候创建

	/** Condition for waiting takes */
	private final Condition notEmpty;//队列为空的时候阻塞,创建对象的时候创建

	/** Condition for waiting puts */
	private final Condition notFull;//队列满的时候阻塞,创建对象的时候创建
	
	//默认使用非公平锁，需要指定容量
	public ArrayBlockingQueue(int capacity) {
		this(capacity, false);
	}
	public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
		//四个创建
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }
}

下面来看3组入队和出队的方法

add(obb),remove(obj)   队列满的时候添加或者空的时候获取会抛出异常

offer(obj),poll()   offer的时候返回true或者false

public boolean offer(E e) {
	checkNotNull(e);//e非空校验，空的话直接抛出异常
	//加锁  阻塞队列的方法基本都是加了锁的比如contains,remove(obj)
	final ReentrantLock lock = this.lock;
	lock.lock();
	try {
		//队列满了，直接抛出异常
		if (count == items.length)
			return false;
		else {
			//入队列，来看看这个私有的入队方法
			enqueue(e);
			return true;
		}
	} finally {
		lock.unlock();
	}
}
private void enqueue(E x) {
	// assert lock.getHoldCount() == 1;
	// assert items[putIndex] == null;
	final Object[] items = this.items;
	items[putIndex] = x;//放入元素
	//如果下标达到了数组长度，那么策略是下一个放入下标置为0
	if (++putIndex == items.length)//更新入队下标
		putIndex = 0;
	count++;
	notEmpty.signal();//唤醒非空线程
}

再看出队方法，同样是先加锁，然后判断当前数量我否为0，是的话返回null，否则
进行出队

public E poll() {
	final ReentrantLock lock = this.lock;
	lock.lock();
	try {
		return (count == 0) ? null : dequeue();
	} finally {
		lock.unlock();
	}
}

那么再来看dequeue方法
private E dequeue() {
	final Object[] items = this.items;
	@SuppressWarnings("unchecked")
	E x = (E) items[takeIndex];//获取当前位置元素
	items[takeIndex] = null;//元素置null
	//更新下一个出队下标位置,下标位置达到了长度，那么从首位置开始
	if (++takeIndex == items.length)
		takeIndex = 0;
	count--;
	if (itrs != null)//这是个迭代器，implements Iterator<E>
		itrs.elementDequeued();
	notFull.signal();//唤醒非满线程
	return x;
}

最后再来看put, take    核心：阻塞方法，队列空的时候take阻塞。队列满的时候put阻塞

/**
 * Inserts the specified element at the tail of this queue, waiting
 * for space to become available if the queue is full.
 */
public void put(E e) throws InterruptedException {
	checkNotNull(e);
	final ReentrantLock lock = this.lock;
	lock.lockInterruptibly();//可响应中断式的获取锁
	try {
		//队列满了，那么线程进入阻塞，注意是while而不是if哦!
		while (count == items.length)
			notFull.await();
		enqueue(e);
	} finally {
		lock.unlock();
	}
}
