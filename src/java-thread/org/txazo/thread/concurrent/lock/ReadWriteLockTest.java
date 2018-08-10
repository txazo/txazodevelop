package org.txazo.thread.concurrent.lock;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.thread.annotation.ThreadSafe;

/**
 * 读写锁
 */
public class ReadWriteLockTest extends BaseTest {

	@Test
	public void testReadWriteLock() throws InterruptedException {
		Counter counter = new Counter();
		Runnable readThread = new ReadThread(counter);
		Runnable writeThread = new WriteThread(counter);

		Thread t1 = new Thread(readThread);
		Thread t2 = new Thread(readThread);
		Thread t3 = new Thread(readThread);
		Thread t4 = new Thread(writeThread);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();

		Assert.assertEquals(1001, counter.incr().intValue());
	}

	/**
	 * 自定义可重入读写锁
	 * 
	 * <pre>
	 * 1) 读操作，无写锁和写请求
	 * 2) 写操作，无读锁和写锁
	 * 3) 读写可重入，读锁重入读锁，写锁重入写锁
	 * 4) 读锁升级到写锁，唯一拥有读锁的线程可以获得写锁
	 * 5) 写锁降级到读锁，拥有写锁的线程可以获得读锁
	 * </pre>
	 */
	public class ReadWriteLock {

		private int writeAccesses = 0;
		private int writeRequests = 0;
		private Thread writingThread = null;
		private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

		public synchronized void lockRead() throws InterruptedException {
			Thread callingThread = Thread.currentThread();
			while (!canGrantReadAccess(callingThread)) {
				wait();
			}
			readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
		}

		public synchronized void unLockRead() {
			Thread callingThread = Thread.currentThread();
			if (!isReader(callingThread)) {
				throw new IllegalMonitorStateException(callingThread.getName() + " does not hold a read lock on this ReadWriteLock");
			}
			int accessCount = getReadAccessCount(callingThread);
			if (accessCount == 1) {
				readingThreads.remove(callingThread);
			} else {
				readingThreads.put(callingThread, accessCount - 1);
			}
			notifyAll();
		}

		public synchronized void lockWrite() throws InterruptedException {
			writeRequests++;
			Thread callingThread = Thread.currentThread();
			while (!canGrantWriteAccess(callingThread)) {
				wait();
			}
			writeRequests--;
			writeAccesses++;
			writingThread = callingThread;
		}

		public synchronized void unLockWrite() {
			Thread callingThread = Thread.currentThread();
			if (!isWriter(callingThread)) {
				throw new IllegalMonitorStateException(callingThread.getName() + " does not hold a write lock on this ReadWriteLock");
			}
			writeAccesses--;
			if (writeAccesses == 0) {
				writingThread = null;
			}
			notifyAll();
		}

		private boolean canGrantReadAccess(Thread callingThread) {
			/** 写锁重入读锁 */
			if (isWriter(callingThread)) {
				return true;
			}
			if (hasWriter()) {
				return false;
			}
			/** 读锁 重入读锁 */
			if (isReader(callingThread)) {
				return true;
			}
			if (hasWriteRequests()) {
				return false;
			}
			return true;
		}

		private boolean canGrantWriteAccess(Thread callingThread) {
			/** 读锁重入写锁 */
			if (isOnlyReader(callingThread)) {
				return true;
			}
			if (hasReaders()) {
				return false;
			}
			if (!hasWriter()) {
				return true;
			}
			if (!isWriter(callingThread)) {
				return false;
			}
			/** 写锁重入写锁 */
			return true;
		}

		private int getReadAccessCount(Thread callingThread) {
			Integer accessCount = readingThreads.get(callingThread);
			return accessCount != null ? accessCount : 0;
		}

		private boolean hasReaders() {
			return readingThreads.size() > 0;
		}

		private boolean isReader(Thread callingThread) {
			return readingThreads.get(callingThread) != null;
		}

		private boolean isOnlyReader(Thread callingThread) {
			return readingThreads.size() == 1 && isReader(callingThread);
		}

		private boolean hasWriter() {
			return writingThread != null;
		}

		private boolean isWriter(Thread callingThread) {
			return writingThread == callingThread;
		}

		private boolean hasWriteRequests() {
			return writeRequests > 0;
		}

	}

	@ThreadSafe
	public class Counter {

		private int count = 0;
		private ReadWriteLock lock = new ReadWriteLock();

		public Integer getCount() {
			try {
				lock.lockRead();
				return count;
			} catch (InterruptedException e) {
			} finally {
				lock.unLockRead();
			}
			return null;
		}

		public Integer incr() {
			try {
				lock.lockWrite();
				return ++count;
			} catch (InterruptedException e) {
			} finally {
				lock.unLockWrite();
			}
			return null;
		}
	}

	public class ReadThread implements Runnable {

		private Counter counter;

		private ReadThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			for (int i = 0; i < 1000; i++) {
				logger.info("{} read {}", threadName, counter.getCount());
			}
		}

	}

	public class WriteThread implements Runnable {

		private Counter counter;

		private WriteThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			for (int i = 0; i < 1000; i++) {
				logger.info("{} write {}", threadName, counter.incr());
			}
		}

	}

}
