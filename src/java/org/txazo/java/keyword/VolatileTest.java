package org.txazo.java.keyword;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * volatile关键字
 * 
 * <pre>
 * volatile只保证从主内存(堆)加载到线程工作内存的值是最新的
 * volatile不能保证操作的原子性
 * </pre>
 */
public class VolatileTest extends BaseTest {

	@Test
	public void testVolatile() throws InterruptedException {
		Thread thread = new Thread(new VolatileRunnable());
		thread.start();
		thread.join();
	}

	private class VolatileRunnable implements Runnable {

		private volatile int value = 0;

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						value++;
					}
				}).start();
			}
			logger.info("value: {}", value);
		}

	}

}
