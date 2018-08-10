package org.txazo.pattern.structural.proxy.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxyTest {

	@Test
	public void testProxy() {
		Sourcable sourcable = new Proxy();
		sourcable.operation();
	}

}
