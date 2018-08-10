package org.txazo.pattern.structural.proxy.dynamic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxyTest {

	@Test
	public void testProxy() {
		Sourcable sourcable = (Sourcable) new DynamicProxy().bind(new Source());
		sourcable.source();
	}

}
