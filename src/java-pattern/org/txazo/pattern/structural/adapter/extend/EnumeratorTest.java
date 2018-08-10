package org.txazo.pattern.structural.adapter.extend;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class EnumeratorTest extends BaseTest {

	@Test
	public void testEnumerator() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		Enumeration<Integer> enumeration = new Enumerator<Integer>(list.iterator());
		while (enumeration.hasMoreElements()) {
			logger.info(enumeration.nextElement().toString());
		}
	}

}
