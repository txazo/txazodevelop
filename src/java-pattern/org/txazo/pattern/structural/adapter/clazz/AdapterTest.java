package org.txazo.pattern.structural.adapter.clazz;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AdapterTest extends BaseTest {

	@Test
	public void testAdapter() {
		Target target = new Adapter();
		target.request();
	}

}
