package org.txazo.jvm.jconsole;

import java.util.ArrayList;
import java.util.List;

/**
 * JConsole内存
 * 
 * <pre>
 * VM Args: -Xms100m -Xmx100m -XX:+UseSerialGC
 * </pre>
 */
public class JConsoleMemoryTest {

	public static class OOMObject {

		public byte[] data = new byte[1024 * 64];

	}

	private static void fillHeap(int size) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0; i < size; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}

	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
	}

}
