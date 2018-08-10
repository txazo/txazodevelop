package org.txazo.pattern.structural.adapter.extend;

import java.util.Iterator;
import java.util.Properties;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class IterationTest extends BaseTest {

	@Test
	public void testIteration() {
		Properties prop = new Properties();
		for (int i = 0; i < 10; i++) {
			prop.put(i, i);
		}

		Iterator<Object> iterator = new Iteration<Object>(prop.elements());
		while (iterator.hasNext()) {
			logger.info(iterator.next().toString());
		}
	}

}
