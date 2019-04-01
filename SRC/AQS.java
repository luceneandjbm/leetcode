AbstractQueuedSynchronizer是实现锁的关键了,同步器内部维护一个FIFO双向队列来完成
同步状态管理，使用volatile类型的state变量来维护同步状态
那么如何来实现一个Lock锁呢？
通常是自定义一个同步器内部类（作为同步组件Lock的内部类）去继承AbstractQueuedSynchronizer
然后重写tryAcquire和tryRelease方法（一般都会实现这两个）这两个方法是用来获取和释放同步
状态用的，AbstractQueuedSynchronizer里面是直接抛出异常

下面来看源码给的一个非重入锁的例子，使用0代表非锁定状态，1表示锁定状态
* <p>Here is a non-reentrant mutual exclusion lock class that uses
 * the value zero to represent the unlocked state, and one to
 * represent the locked state. While a non-reentrant lock
 * does not strictly require recording of the current owner
 * thread, this class does so anyway to make usage easier to monitor.
 * It also supports conditions and exposes
 * one of the instrumentation methods:
 *
 *  <pre> {@code
 * class Mutex implements Lock, java.io.Serializable {
 *
 *   // Our internal helper class
 *   private static class Sync extends AbstractQueuedSynchronizer {
 *     // Reports whether in locked state
 *     protected boolean isHeldExclusively() {
 *       return getState() == 1;
 *     }
 *
 *     // Acquires the lock if state is zero
 *     public boolean tryAcquire(int acquires) {
 *       assert acquires == 1; // Otherwise unused
 *       if (compareAndSetState(0, 1)) {
 *         setExclusiveOwnerThread(Thread.currentThread());
 *         return true;
 *       }
 *       return false;
 *     }
 *
 *     // Releases the lock by setting state to zero
 *     protected boolean tryRelease(int releases) {
 *       assert releases == 1; // Otherwise unused
 *       if (getState() == 0) throw new IllegalMonitorStateException();
 *       setExclusiveOwnerThread(null);
 *       setState(0);
 *       return true;
 *     }
 *
 *     // Provides a Condition
 *     Condition newCondition() { return new ConditionObject(); }
 *
 *     // Deserializes properly
 *     private void readObject(ObjectInputStream s)
 *         throws IOException, ClassNotFoundException {
 *       s.defaultReadObject();
 *       setState(0); // reset to unlocked state
 *     }
 *   }
 *
 *   // The sync object does all the hard work. We just forward to it.
 *   private final Sync sync = new Sync();
 *
 *   public void lock()                { sync.acquire(1); }
 *   public boolean tryLock()          { return sync.tryAcquire(1); }
 *   public void unlock()              { sync.release(1); }
 *   public Condition newCondition()   { return sync.newCondition(); }
 *   public boolean isLocked()         { return sync.isHeldExclusively(); }
 *   public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
 *   public void lockInterruptibly() throws InterruptedException {
 *     sync.acquireInterruptibly(1);
 *   }
 *   public boolean tryLock(long timeout, TimeUnit unit)
 *       throws InterruptedException {
 *     return sync.tryAcquireNanos(1, unit.toNanos(timeout));
 *   }
 * }}</pre>

 为了方便观看，先把AbstractQueuedSynchronizer的内部类Node列出来
static final class Node {
	/** Marker to indicate a node is waiting in shared mode */
	static final Node SHARED = new Node();
	/** Marker to indicate a node is waiting in exclusive mode */
	static final Node EXCLUSIVE = null;

	/** waitStatus value to indicate thread has cancelled */
	static final int CANCELLED =  1;
	/** waitStatus value to indicate successor's thread needs unparking */
	static final int SIGNAL    = -1;
	/** waitStatus value to indicate thread is waiting on condition */
	static final int CONDITION = -2;
	/**
	 * waitStatus value to indicate the next acquireShared should
	 * unconditionally propagate
	 */
	static final int PROPAGATE = -3;
	
	volatile int waitStatus;//等待状态，0是普通状态
	volatile Node prev;//前驱节点
	volatile Node next;//后继节点
	volatile Thread thread;//当前线程会封装成node
	Node nextWaiter;//下一个在condition上等待的节点
	/**
	 * Returns true if node is waiting in shared mode.
	 */
	final boolean isShared() {
		return nextWaiter == SHARED;
	}
	/**
	 * Returns previous node, or throws NullPointerException if null.
	 * Use when predecessor cannot be null.  The null check could
	 * be elided, but is present to help the VM.
	 *
	 * @return the predecessor of this node
	 */
	final Node predecessor() throws NullPointerException {
		Node p = prev;
		if (p == null)
			throw new NullPointerException();
		else
			return p;
	}
	Node() {    // Used to establish initial head or SHARED marker
	}

	Node(Thread thread, Node mode) {     // Used by addWaiter
		this.nextWaiter = mode;
		this.thread = thread;
	}

	Node(Thread thread, int waitStatus) { // Used by Condition
		this.waitStatus = waitStatus;
		this.thread = thread;
	}
} 
 
 
public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer
    implements java.io.Serializable {
	//构造方法
	private transient volatile Node head;//头结点  会指向真实存在的节点
	private transient volatile Node tail;//尾节点  会指向真实存在的节点
	private volatile int state;//同步状态
	protected final int getState() {//获取同步状态
        return state;
    }
	protected final void setState(int newState) {//设置同步状态
        state = newState;
    }
	//比较并交换同步状态
	protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
}
OK,有了这些准备那么来看acquire方法（先看独占式的）

public final void acquire(int arg) {
	if (!tryAcquire(arg) &&
		acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
		selfInterrupt();
}

调用自己写的tryAcquire(arg)方法，如果返回了false（没有获取到同步状态），那么会执行
addWaiter(Node.EXCLUSIVE)，注意：节点是独占模式，我们来看改方法

private Node addWaiter(Node mode) {
	//使用当前线程和mode模式构造节点
	Node node = new Node(Thread.currentThread(), mode);
	//尝试快速添加到尾部
	Node pred = tail;
	if (pred != null) {
		node.prev = pred;
		if (compareAndSetTail(pred, node)) {
			pred.next = node;
			return node;
		}
	}
	//如果尝试失败
	enq(node);
	return node;
}
/**
 * 节点添加到队列尾部   死循环的方式添加到队列尾部
 */
private Node enq(final Node node) {
	for (;;) {
		Node t = tail;
		if (t == null) { // Must initialize
			if (compareAndSetHead(new Node()))//设置队头
				tail = head;
		} else {
			node.prev = t;
			if (compareAndSetTail(t, node)) {//设置队尾部
				t.next = node;
				return t;
			}
		}
	}
}

将当前线程构造成节点，并且成功添加到队列尾部的时候,节点就进入自旋的过程（死循环）
这个过程用来判断前驱节点是否为头结点，并且尝试获取同步状态，如果成功的话那么就
将该节点设置为头结点，方法返回.

final boolean acquireQueued(final Node node, int arg) {
	boolean failed = true;
	try {
		boolean interrupted = false;
		for (;;) {//进入自旋
			final Node p = node.predecessor();//获取前驱节点
			//前驱节点是头结点才尝试获取同步状态,获取成功，进行设置头结点并返回
			if (p == head && tryAcquire(arg)) {
				setHead(node);//将node设置为头结点
				p.next = null; // help GC
				failed = false;
				return interrupted;
			}
			//前者返回true表示，当前节点（线程）需要被阻塞
			//那么后者则会对线程进行阻塞，同时阻塞完后会返回是否被中断
			//如果是的话，设置interrupted=true  注意：只有前驱节点（头结点）唤醒了
			//之后才会被调度
			if (shouldParkAfterFailedAcquire(p, node) &&
				parkAndCheckInterrupt())
				interrupted = true;
		}
	} finally {
		if (failed)//如果出现了异常，那么删除该节点
			cancelAcquire(node);
	}
}

/**
* 判断和更新当前acquire失败的节点状态
* 如果返回true表示，当前节点（线程）需要被阻塞
*注意：这是 acquire循环中一个很重要的唤醒（signal）控制方法
*/
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
	int ws = pred.waitStatus;
	if (ws == Node.SIGNAL)
		/*
		 * This node has already set status asking a release
		 * to signal it, so it can safely park.
		 */
		return true;
	if (ws > 0) {
		/*
		 * Predecessor was cancelled. Skip over predecessors and
		 * indicate retry.
		 */
		do {
			node.prev = pred = pred.prev;
		} while (pred.waitStatus > 0);
		pred.next = node;
	} else {
		/*
		 * waitStatus must be 0 or PROPAGATE.  Indicate that we
		 * need a signal, but don't park yet.  Caller will need to
		 * retry to make sure it cannot acquire before parking.
		 */
		compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
	}
	return false;
}


/**
 * 该方法才是用来阻塞线程，并且返回是否被中断
 *
 * @return {@code true} if interrupted
 */
private final boolean parkAndCheckInterrupt() {
	LockSupport.park(this);
	return Thread.interrupted();
}

那么我们来看看LockSupport.park(this)，如下
/**
 * Disables the current thread for thread scheduling purposes unless the
 * permit is available.
 */
public static void park(Object blocker) {
	Thread t = Thread.currentThread();
	setBlocker(t, blocker);
	UNSAFE.park(false, 0L);
	setBlocker(t, null);
}

acquireQueued返回了true后
public final void acquire(int arg) {
	if (!tryAcquire(arg) &&
		acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
		selfInterrupt();
}
那么该方法也走完了，那么lock.lock()方法也返回了，所以可以继续执行后续的逻辑
注意：一开始就获取了同步状态（tryAcquire(arg)返回true）的线程，会直接从acquire
返回，即lock()返回。

对于lock.unlock();里面会调用AQS的release方法，release方法会调用自己写的tryRelease方法
道理跟acquire是一样，下面简单看下。
public final boolean release(int arg) {
	//调用自定义的tryRelease，比如可重入锁，当状态是0的时候返回true表示释放锁
	if (tryRelease(arg)) {
		Node h = head;//获取头结点
		//如果头结点！=null，表示队列还有结点，那么就唤醒后继节点
		if (h != null && h.waitStatus != 0)
			unparkSuccessor(h);
		return true;
	}
	return false;
}
