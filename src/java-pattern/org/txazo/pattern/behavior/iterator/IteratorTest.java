package org.txazo.pattern.behavior.iterator;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class IteratorTest extends BaseTest {

	@Test
	public void testIterator() throws Exception {
		String[] array = { "1", "2", "3", "4", "5" };
		Aggregate<String> aggregate = new Aggregate<String>(array);
		for (Iterator<String> i = aggregate.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}

}
