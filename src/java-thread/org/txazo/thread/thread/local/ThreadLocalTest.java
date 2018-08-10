package org.txazo.thread.thread.local;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.lang.ThreadLocal
 * 
 * <pre>
 * 1) ThreadLocal
 *    ThreadLocal.ThreadLocalMap
 *    ThreadLocal.ThreadLocalMap.Entry
 * 2) ThreadLocal为每一个线程维护一个独立的变量副本
 * 3) 每一个Thread都有一个实例变量ThreadLocalMap<ThreadLocal, Object> threadLocals, 类似HashMap, 线程自己保存独立的变量副本
 * 4) ThreadLocal负责管理ThreadLocalMap, 包括初始化、更新、取值、删除, ThreadLocal为ThreadLocalMap的key
 * 5) 原理:
 *    1) ThreadLocalMap map = Thread.currentThread().threadLocals
 *    2) (key = ThreadLocal) - hash - ThreadLocalMap - Entry - value
 *    3) Entry继承自WeakReference, 弱引用ThreadLocal, 以便垃圾回收
 * 6) ThreadLocalMap: 隔离线程和线程
 *    ThreadLocal: 隔离ThreadLocal和ThreadLocal
 * </pre>
 */
public class ThreadLocalTest extends BaseTest {

	@Test
	public void testThreadLocal() throws InterruptedException {
		ThreadLocalCounter counter = new ThreadLocalCounter();
		new Thread(new MyThread(counter)).start();
		new Thread(new MyThread(counter)).start();
		new Thread(new MyThread(counter)).start();

		Thread.sleep(2000);
	}

	private class MyThread implements Runnable {

		private ThreadLocalCounter counter;

		public MyThread(ThreadLocalCounter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				logger.info("{}\t{}", Thread.currentThread().getName(), counter.getCount());
			}
		}
	}

	private static class ThreadLocalCounter {

		private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {

			/**
			 * 初始化
			 */
			@Override
			protected Integer initialValue() {
				return 0;
			}

		};

		public int getCount() {
			threadLocal.set(threadLocal.get() + 1);
			return threadLocal.get();
		}

	}

}
