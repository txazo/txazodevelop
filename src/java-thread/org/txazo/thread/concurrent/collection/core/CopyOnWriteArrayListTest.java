package org.txazo.thread.concurrent.collection.core;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class CopyOnWriteArrayListTest extends BaseTest {

	private static List<String> list = new CopyOnWriteArrayList<String>();

	@Test
	public void testCopyOnWriteArrayList() throws InterruptedException {
		new Thread(new MyThread("a")).start();
		new Thread(new MyThread("b")).start();
		new Thread(new MyThread("c")).start();
		new Thread(new MyThread("d")).start();

		Thread.sleep(2000);
	}

	private static void showList() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> i = list.iterator(); i.hasNext();) {
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
			for (int i = 0; i < 6; i++) {
				list.add(name + "-" + i);
				showList();
			}
		}
	}

}
