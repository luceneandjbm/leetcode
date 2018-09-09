LinkedBlockingQueue解读：

首先默认这是个无界的阻塞队列，来看构造方法

public LinkedBlockingQueue() {
	this(Integer.MAX_VALUE);
}
public LinkedBlockingQueue(int capacity) {
	if (capacity <= 0) throw new IllegalArgumentException();
	this.capacity = capacity;
	last = head = new Node<E>(null);
}
可以看到默认的大小是int的最大值，同时创建对象的时候会将last（后续会指向实际节点）
和head初始化，不过值为null
这点是区别与LinkedList的，它的first和last是指向实际的节点的

来看Node结构
static class Node<E> {
	E item;

	Node<E> next;//单链表

	Node(E x) { item = x; }
}
public class LinkedBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable {
			
	/** The capacity bound, or Integer.MAX_VALUE if none */
    private final int capacity;//容量

    /** Current number of elements */
    private final AtomicInteger count = new AtomicInteger();//元素数量

    /**
     * Head of linked list.
     * Invariant: head.item == null
     */
    transient Node<E> head;//头结点

    /**
     * Tail of linked list.
     * Invariant: last.next == null
     */
    private transient Node<E> last;//尾节点

    /** Lock held by take, poll, etc */
    private final ReentrantLock takeLock = new ReentrantLock();

    /** Wait queue for waiting takes */
    private final Condition notEmpty = takeLock.newCondition();

    /** Lock held by put, offer, etc */
    private final ReentrantLock putLock = new ReentrantLock();

    /** Wait queue for waiting puts */
    private final Condition notFull = putLock.newCondition();
}
可以发现与ArrayBlockQueue的区别在于，使用了take锁和put锁。而且数量统计使用原子操作类

来分析它的put方法
public void put(E e) throws InterruptedException {
	if (e == null) throw new NullPointerException();
	// Note: convention in all put/take/etc is to preset local var
	// holding count negative to indicate failure unless set.
	int c = -1;
	Node<E> node = new Node<E>(e);
	final ReentrantLock putLock = this.putLock;
	final AtomicInteger count = this.count;
	putLock.lockInterruptibly();//加put锁
	try {
		while (count.get() == capacity) {//容量满了那么生产者进入阻塞
			notFull.await();
		}
		enqueue(node);//入队方法很简单，如下
		//数量原子式增加,why？因为生产者和消费者使用了不同的锁导致了不会同步
		//也许生产者此时c=3，准备+1的时候，消费者已经取走了一个，此时还是应该=3
		c = count.getAndIncrement();
		if (c + 1 < capacity)
			notFull.signal();//注意：是唤醒生产者线程
	} finally {
		putLock.unlock();
	}
	//注意这里，明明前面入队了为什么这里还会判断c==0?
	//虽然入队了，但是唤醒了消费者，所以可能被消费了(因为锁不一样不会同步)
	//临界情况
	if (c == 0)//c==0表示还有一个元素
		signalNotEmpty();//唤醒消费者
}

private void enqueue(Node<E> node) {
	last = last.next = node;
}

take方法大同小异
public E take() throws InterruptedException {
	E x;
	int c = -1;
	final AtomicInteger count = this.count;
	final ReentrantLock takeLock = this.takeLock;
	takeLock.lockInterruptibly();
	try {
		while (count.get() == 0) {
			notEmpty.await();
		}
		x = dequeue();
		c = count.getAndDecrement();
		if (c > 1)//元素数量大于1，那么唤醒消费者继续消费
			notEmpty.signal();
	} finally {
		takeLock.unlock();
	}
	//实际的c是需要-1的，所以此时就可以继续生产了，唤醒生产者
	//临界情况
	if (c == capacity)
		signalNotFull();
	return x;
}

private E dequeue() {
	// assert takeLock.isHeldByCurrentThread();
	// assert head.item == null;
	Node<E> h = head;
	Node<E> first = h.next;
	h.next = h; // help GC
	head = first;
	E x = first.item;
	first.item = null;
	return x;
}
