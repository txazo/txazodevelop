package org.txazo.cache.memcached.hash;

import org.apache.commons.lang3.RandomStringUtils;
import org.txazo.test.base.BaseTest;

public class ConsistentMemcachedTest extends BaseTest {

	public static void main(String[] args) {
		testConsistentMemcached();
	}

	public static void testConsistentMemcached() {
		MemcachedManager manager = new ConsistentMemcachedManager(200);

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		new Thread(new MyThread(manager)).start();

		try {
			Thread.sleep(1000 * 6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class MyThread implements Runnable {

		private MemcachedManager manager;
		private static final String randomStr = "qwertyuiopasdfghjklzxcvbnm0123456789";

		public MyThread(MemcachedManager manager) {
			this.manager = manager;
		}

		@Override
		public void run() {
			String key = null;
			for (int i = 0; i < 10000; i++) {
				key = RandomStringUtils.random(6, randomStr);
				logger.info("{}\t{}\t{}", i, key, manager.get(key));
			}
		}

	}

}
