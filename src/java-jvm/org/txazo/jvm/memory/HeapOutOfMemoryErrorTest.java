package org.txazo.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Stack溢出
 * 
 * <pre>
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * </pre>
 */
public class HeapOutOfMemoryErrorTest {

	private static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}

}
