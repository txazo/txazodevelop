package org.txazo.thread.thread.lang;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * volatile关键字
 * 
 * <pre>
 * 1) volatile关键字迫使所有线程均读写主内存中对应的变量
 * 2) volatile变量在多线程间可见
 * 3) volatile变量不具备原子特性
 * 4) JVM保证从主内存加载到线程工作内存的volatile变量的值是最新的
 * </pre>
 * 
 * @author txazo
 * 
 */
public class VolatileTest extends BaseTest {

	@Test
	public void testVolatile() throws InterruptedException {
		final VolatileWrapper volatileWrapper = new VolatileWrapper();

		Thread t1 = new Thread() {

			@Override
			public void run() {
				while (volatileWrapper.available) {
					volatileWrapper.exists();
				}
			}

		};

		Thread t2 = new Thread() {

			@Override
			public void run() {
				while (volatileWrapper.available) {
					volatileWrapper.swapValue();
				}
			}

		};

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private class VolatileWrapper {

		private volatile boolean exists;
		public volatile boolean available = true;

		public void exists() {
			if (exists == !exists) {
				logger.info("exists == !exists");
				available = false;
			}
		}

		public void swapValue() {
			exists = !exists;
		}

	}

}
