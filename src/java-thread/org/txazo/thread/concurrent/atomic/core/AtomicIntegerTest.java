package org.txazo.thread.concurrent.atomic.core;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AtomicIntegerTest extends BaseTest {

	@Test
	public void testAtomicInteger() throws InterruptedException {
		AtomicInteger count = new AtomicInteger(1);
		System.out.println(count.getAndIncrement());
		System.out.println(count.incrementAndGet());
		System.out.println(count.getAndAdd(2));
		System.out.println(count.addAndGet(5));
		System.out.println(count.getAndSet(15));
		System.out.println(count.get());
		count.set(20);
		System.out.println(count.get());

		Runnable thread = new MyThread();
		new Thread(thread).start();
		new Thread(thread).start();
		new Thread(thread).start();

		Thread.sleep(2000);
	}

	private class MyThread implements Runnable {

		private AtomicInteger count = new AtomicInteger(10);

		@Override
		public void run() {
			while (count.get() > 0) {
				System.out.println(Thread.currentThread().getName() + "\t" + count.getAndDecrement());

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
