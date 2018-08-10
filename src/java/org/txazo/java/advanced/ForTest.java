package org.txazo.java.advanced;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 增强For循环
 */
public class ForTest extends BaseTest {

	@Test
	public void testFor() {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		for (Integer i : list) {
			logger.info("{}", i);
		}
	}

}
