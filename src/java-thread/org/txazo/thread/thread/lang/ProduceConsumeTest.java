package org.txazo.thread.thread.lang;

import java.util.Random;
import java.util.Stack;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 生产者消费者模式 - wait/notify实现
 * 
 * @author txazo
 * 
 */
public class ProduceConsumeTest extends BaseTest {

	@Test
	public void testProduceConsume() throws InterruptedException {
		Container container = new Container(5);
		Thread producer = new Thread(new Producer(container));
		Thread consumer = new Thread(new Consumer(container));

		producer.start();
		consumer.start();

		producer.join();
		consumer.join();
	}

	private class Container {

		private int count = 0;
		private int maxium = 5;
		private Stack<String> messages = new Stack<String>();

		public Container(int maxium) {
			if (maxium < 1) {
				throw new IllegalArgumentException("");
			}
			this.maxium = maxium;
		}

		public synchronized void produce(String message) {
			while (count == maxium) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			messages.push(message);
			logger.info("Produce {}", message);

			count++;
			notifyAll();
		}

		public synchronized String consume() {
			while (count == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			String message = messages.pop();
			logger.info("Consume {}", message);

			count--;
			notifyAll();

			return message;
		}

	}

	private class Producer implements Runnable {

		private Container container;
		private Random random = new Random();

		public Producer(Container container) {
			this.container = container;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				container.produce(String.valueOf(i));

				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private class Consumer implements Runnable {

		private Container container;
		private Random random = new Random();

		public Consumer(Container container) {
			this.container = container;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				container.consume();

				try {
					Thread.sleep(random.nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
