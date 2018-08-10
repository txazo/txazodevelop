package org.txazo.pattern.structural.adapter.object;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AdapterTest extends BaseTest {

	@Test
	public void testAdapter() {
		Adaptee adaptee = new Adaptee();
		Target target = new Adapter(adaptee);
		target.request();
	}

}
