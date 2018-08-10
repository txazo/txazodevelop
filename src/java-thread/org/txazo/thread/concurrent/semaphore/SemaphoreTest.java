package org.txazo.thread.concurrent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 信号量
 * 
 * @author txazo
 * 
 */
public class SemaphoreTest extends BaseTest {

	@Test
	public void testSemaphore() throws InterruptedException {
		Access access = new Access();
		AccessThread accessThread = new AccessThread(access);
		for (int i = 0; i < 10; i++) {
			new Thread(accessThread).start();
		}

		Thread.sleep(10000);
	}

	public class Access {

		private Semaphore semaphore = new Semaphore(5);

		public void access() {
			try {
				semaphore.acquire();
				logger.info("{} access", Thread.currentThread().getName());
				Thread.sleep(ThreadLocalRandom.current().nextInt(5000) + 1000);
			} catch (InterruptedException e) {
			} finally {
				semaphore.release();
			}

		}

	}

	public class AccessThread implements Runnable {

		private Access access;

		private AccessThread(Access access) {
			this.access = access;
		}

		@Override
		public void run() {
			access.access();
		}

	}

}
