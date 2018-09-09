ReentrantLock解读：

根据AQS解读所说的，ReentrantLock就是一个自定义的同步组件，内部用友一个同步器且实现了AQS
在ReentrantLock里面就是Sync

public class ReentrantLock implements Lock, java.io.Serializable {
    
    /** Synchronizer providing all implementation mechanics */
    private final Sync sync;
	abstract static class Sync extends AbstractQueuedSynchronizer {
		abstract void lock();
		//为非公平锁准备的
		final boolean nonfairTryAcquire(int acquires) {
            //代码略
        }

        protected final boolean tryRelease(int releases) {
            //代码略
        }

        protected final boolean isHeldExclusively() {
            //代码略
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        final Thread getOwner() {
            return getState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getState() : 0;
        }

        final boolean isLocked() {
            return getState() != 0;
        }
    }
}

对比AQS中的例子发现是否很类似呢，即：同步组件里面用友一个同步器，该同步器继承AQS，同时
重写了tryAcquire和tryRelease方法，当然根据需要还有其他方法
	
下面从创建到使用开始说

ReentrantLock lock = new ReentrantLock();
我们查看构造方法
public ReentrantLock() {
	sync = new NonfairSync();
}
public ReentrantLock(boolean fair) {
	sync = fair ? new FairSync() : new NonfairSync();
}

创建ReentrantLock对象的时候其实会创建Sync对象，但是在ReentrantLock里面分为公平的Sync
和非公平的Sync，默认是非公平的。
1、公平：a，b，c线程一次进入等待状态，那么后面a，b，c线程会按顺序获得锁。
2、非公平：后来者可能比等了很久的线程先获得锁
或者说
1、公平锁能保证：老的线程排队使用锁，新线程仍然排队使用锁。 
2、非公平锁保证：老的线程排队使用锁；但是无法保证新线程抢占已经在排队的线程的锁。

这两把锁都继承Sync作为ReentrantLock的内部类

那么先来看非公平锁，使用lock.lock()的调用轨迹如下
public void lock() {
	sync.lock();//非公平锁的lock
}

//非公平锁
static final class NonfairSync extends Sync {
	private static final long serialVersionUID = 7316153563782823691L;

	/**
	 * Performs lock.  Try immediate barge, backing up to normal
	 * acquire on failure.
	 */
	final void lock() {
		//尝试快速设置同步状态
		if (compareAndSetState(0, 1))
			//设置成功，设置线程独占
			setExclusiveOwnerThread(Thread.currentThread());
		else
			//设置状态失败，调用AQS的acquire方法，接下来在acquire方法里面会调用
			//下面的tryAcquire方法尝试获取同步状态，获取失败的线程会进入阻塞
			acquire(1);
	}

	protected final boolean tryAcquire(int acquires) {
		return nonfairTryAcquire(acquires);//在Sync中定义，如果
	}
}
/**
 * Performs non-fair tryLock.  tryAcquire is implemented in
 * subclasses, but both need nonfair try for trylock method.
 */
final boolean nonfairTryAcquire(int acquires) {
	final Thread current = Thread.currentThread();
	int c = getState();//获取同步状态
	if (c == 0) {
		//如果同步状态是0，那么CAS设置同步状态,设置成功那么设置独占。
		//不过肯定是不可能了，因为在上面的if里面
		//已经设置成了1，所以不会进入这部分代码块
		//不过CAS设置的线程执行完后，后续的线程是会执行的
		if (compareAndSetState(0, acquires)) {
			setExclusiveOwnerThread(current);
			return true;
		}
	}
	//如果当前线程为独占了的线程（表示重入了），那么设置同步状态
	//比如上面的CAS设置了1的线程，再次进入同一把锁的同步块，此时设置同步状态为1+1
	else if (current == getExclusiveOwnerThread()) {
		int nextc = c + acquires;
		if (nextc < 0) // overflow
			throw new Error("Maximum lock count exceeded");
		setState(nextc);//更新同步状态
		return true;//返回获取成功。
	}
	//CAS设置失败了的线程那么就只能返回fasle，添加到队列中等待被唤醒了。
	return false;
}

接下来再看公平锁

/**
 * Sync object for fair locks
 */
static final class FairSync extends Sync {
	private static final long serialVersionUID = -3000897897090466540L;

	final void lock() {
		//对比非公平锁，没有了CAS设置同步状态了，所以新来的线程在这部分
		//就没有插队获取同步状态了，统一要走acquire然后走tryAcquire
		acquire(1);
	}

	/**
	 * Fair version of tryAcquire.  Don't grant access unless
	 * recursive call or no waiters or is first.
	 */
	//这个tryAcquire就是公平锁自己实现了，而不是继承Sync
	protected final boolean tryAcquire(int acquires) {
		final Thread current = Thread.currentThread();
		int c = getState();
		if (c == 0) {
			//与非公平锁比较，差别就在多了hasQueuedPredecessors
			if (!hasQueuedPredecessors() &&
				compareAndSetState(0, acquires)) {
				setExclusiveOwnerThread(current);
				return true;
			}
		}
		else if (current == getExclusiveOwnerThread()) {
			int nextc = c + acquires;
			if (nextc < 0)
				throw new Error("Maximum lock count exceeded");
			setState(nextc);
			return true;
		}
		return false;
	}
}

//与非公平锁比较，差别就在多了hasQueuedPredecessors
注意这里的hasQueuedPredecessors方法，这个方法在非公平锁里面是没有的，恰恰是这个方法保证了
新来的线程不能插队，需要先处理队列中的线程，新来的线程只能去队列里面排队。
而非公平锁就不能保证了，可能队列里面的老线程还没处理，新来的线程就插队来处理了。
//该方法用来判断队列里面是否有前驱节点
//简单来说，如果新线程想插队，发现队列里面还有节点那么它只能去队列尾部去等待了
public final boolean hasQueuedPredecessors() {
	// The correctness of this depends on head being initialized
	// before tail and on head.next being accurate if the current
	// thread is first in queue.
	Node t = tail; // Read fields in reverse initialization order
	Node h = head;
	Node s;
	return h != t &&
		//判断s节点是否为当前节点,后面会将s设置为头节点
		((s = h.next) == null || s.thread != Thread.currentThread());
}

下面来看重入锁的重入是怎么实现的，其实前面已经说过，这里就拿非公平锁来说


final boolean nonfairTryAcquire(int acquires) {
	final Thread current = Thread.currentThread();
	int c = getState();
	if (c == 0) {
		if (compareAndSetState(0, acquires)) {
			setExclusiveOwnerThread(current);
			return true;
		}
	}
	else if (current == getExclusiveOwnerThread()) {
		int nextc = c + acquires;
		if (nextc < 0) // overflow
			throw new Error("Maximum lock count exceeded");
		setState(nextc);
		return true;
	}
	return false;
}
		
	从第二个if else里面，判断当前线程是否为获取锁的线程，是的话那么将同步状态值
进行增加并返回成功，表示获取锁状态成功，成功获取锁的线程再次获取锁的时候
只是增加了同步状态值，释放的时候减少同步状态值，如下	
		
protected final boolean tryRelease(int releases) {
	int c = getState() - releases;
	if (Thread.currentThread() != getExclusiveOwnerThread())
		throw new IllegalMonitorStateException();
	boolean free = false;
	if (c == 0) {
		free = true;
		setExclusiveOwnerThread(null);
	}
	setState(c);
	return free;
}
可以看到释放锁的时候，每次减少同步状态值，当同步状态值为0的时候释放锁。
