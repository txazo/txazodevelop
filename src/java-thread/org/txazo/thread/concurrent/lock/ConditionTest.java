package org.txazo.thread.concurrent.lock;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.locks.Condition
 * 
 * @author txazo
 * 
 */
public class ConditionTest extends BaseTest {

	@Test
	public void testCondition() throws InterruptedException {
		Container container = new Container(5);
		Thread producer = new Thread(new Producer(container));
		Thread consumer = new Thread(new Consumer(container));

		producer.start();
		consumer.start();

		producer.join();
		consumer.join();
	}

	private class Container {

		private int size = 0;
		private int maxCapacity = 10;
		private final Lock lock = new ReentrantLock();
		private final Condition notFull = lock.newCondition();
		private final Condition notEmpty = lock.newCondition();
		private final Stack<String> goods = new Stack<String>();

		public Container(int maxCapacity) {
			if (maxCapacity > 0) {
				this.maxCapacity = maxCapacity;
			}
		}

		public void put(String good) {
			lock.lock();
			try {
				while (size == maxCapacity) {
					notFull.await();
				}

				size++;
				goods.push(good);

				notEmpty.signal();
			} catch (Exception e) {
			} finally {
				lock.unlock();
			}
		}

		public String take() {
			String good = null;
			lock.lock();
			try {
				while (size == 0) {
					notEmpty.await();
				}

				size--;
				good = goods.pop();

				notFull.signal();
			} catch (Exception e) {
			} finally {
				lock.unlock();
			}
			return good;
		}

	}

	private class Producer implements Runnable {

		private Container container;

		private Producer(Container container) {
			this.container = container;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				container.put(String.valueOf(i));
				logger.info("put {}", i);
			}
		}
	}

	private class Consumer implements Runnable {

		private Container container;

		private Consumer(Container container) {
			this.container = container;
		}

		@Override
		public void run() {
			Random random = ThreadLocalRandom.current();
			for (int i = 0; i < 100; i++) {
				logger.info("take {}", container.take());

				try {
					Thread.sleep(random.nextInt(100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
