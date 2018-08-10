package org.txazo.thread.thread.lang;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.lang.Runnable
 * 
 * @author txazo
 * 
 */
public class RunnableTest extends BaseTest {

	@Test
	public void testRunnable() throws InterruptedException {
		Runnable runnable = new MyRunnable();

		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}

		Thread.sleep(6000);
	}

	/**
	 * Runnable
	 * 
	 * <pre>
	 * 1) 局部变量是线程安全的
	 * </pre>
	 * 
	 */
	private class MyRunnable implements Runnable {

		@Override
		public void run() {
			Random random = ThreadLocalRandom.current();
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logger.info("{} is running", Thread.currentThread().getName());
		}

	}

}
