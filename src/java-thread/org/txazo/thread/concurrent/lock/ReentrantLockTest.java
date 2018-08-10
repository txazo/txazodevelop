package org.txazo.thread.concurrent.lock;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.locks.ReentrantLock - 重入锁
 * 
 * <pre>
 * lock() 获得锁，如果锁已被占用，则等待
 * lockInterruptibly() 同lock()，但优先响应中断
 * tryLock() 尝试获得锁，不等待，立即返回
 * tryLock(long timeout, TimeUnit unit) 在给定时间内尝试获得锁
 * unlock() 释放锁
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ReentrantLockTest extends BaseTest {

	@Test
	public void testReentrantLock() throws InterruptedException {
		MyRunnable runnable = new MyRunnable(new Counter());
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private class Counter {

		private int count;

		/**
		 * <pre>
		 * Lock lock = new ReentrantLock(true) 公平锁
		 * Lock lock = new ReentrantLock(false) 非公平锁，性能好
		 * </pre>
		 */
		private final Lock lock = new ReentrantLock(false);

		public int incr() {
			int value = -1;
			lock.lock();
			try {
				value = ++count;
			} finally {
				lock.unlock();
			}
			return value;
		}

		public int decr() {
			int value = -1;
			lock.lock();
			try {
				value = --count;
			} finally {
				lock.unlock();
			}
			return value;
		}

	}

	private class MyRunnable implements Runnable {

		private Counter counter;

		private MyRunnable(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			Random random = ThreadLocalRandom.current();
			for (int i = 0; i < 100; i++) {
				if (random.nextInt() % 2 == 0) {
					logger.info("incr {}", counter.incr());
				} else {
					logger.info("decr {}", counter.decr());
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
