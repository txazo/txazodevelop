package org.txazo.thread.concurrent.collection.core;

import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * LinkedBlockingDeque
 * 
 * @author txazo
 * 
 */
public class LinkedBlockingDequeTest extends BaseTest {

	private static BlockingDeque<String> queue = new LinkedBlockingDeque<String>();

	@Test
	public void testLinkedBlockingDeque() throws InterruptedException {
		new Thread(new MyThread("a")).start();

		Thread.sleep(2000);
	}

	private static void showQueue() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> i = queue.iterator(); i.hasNext();) {
			sb.append(i.next() + ", ");
		}
		String line = sb.toString();
		System.out.println(line.substring(0, line.length() - 2));
	}

	private class MyThread implements Runnable {

		private String name;

		public MyThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			String id = null;
			for (int i = 0; i < 6; i++) {
				id = name + "-" + i;
				if (i < 2) {
					/** 插入元素到队列尾部 */
					queue.offer(id);
				} else if (i < 4) {
					/** 插入元素到队列头部 */
					queue.offerFirst(id);
				} else if (i < 5) {
					/** 获取并移除队列的头部 */
					queue.poll();
				} else if (i < 6) {
					/** 获取并移除队列的尾部 */
					queue.pollLast();
				}
				showQueue();
			}
		}
	}

}
