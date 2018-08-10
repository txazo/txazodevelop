package org.txazo.thread.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/***
 * ReentrantLock解决死锁
 * 
 * @author txazo
 * 
 */
public class SafeLockTest extends BaseTest {

	@Test
	public void testSafeLock() throws InterruptedException {
		final Friend f1 = new Friend("root");
		final Friend f2 = new Friend("admin");

		Thread t1 = new Thread(new BowLoop(f1, f2));
		Thread t2 = new Thread(new BowLoop(f2, f1));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private class BowLoop implements Runnable {

		private Friend bower;
		private Friend bowee;

		public BowLoop(Friend bower, Friend bowee) {
			this.bower = bower;
			this.bowee = bowee;
		}

		@Override
		public void run() {
			Random random = new Random();
			while (true) {
				try {
					Thread.sleep(500 + random.nextInt(500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				bowee.bow(bower);
			}
		}

	}

	private class Friend {

		private final String name;
		private final Lock lock = new ReentrantLock();

		public Friend(String name) {
			this.name = name;
		}

		public boolean checkLock(Friend friend) {
			boolean myLock = false;
			boolean yourLock = false;
			try {
				myLock = lock.tryLock();
				yourLock = friend.lock.tryLock();
			} finally {
				if (!(myLock && yourLock)) {
					if (myLock) {
						lock.unlock();
					}
					if (yourLock) {
						friend.lock.unlock();
					}
				}
			}
			return myLock && yourLock;
		}

		public void bow(Friend friend) {
			if (checkLock(friend)) {
				try {
					logger.info("{} bow to {}", name, friend.name);
					friend.bowBack(this);
				} finally {
					lock.unlock();
					friend.lock.unlock();
				}
			}
		}

		private void bowBack(Friend friend) {
			logger.info("{} bowBack to {}", name, friend.name);
		}

	}

}
