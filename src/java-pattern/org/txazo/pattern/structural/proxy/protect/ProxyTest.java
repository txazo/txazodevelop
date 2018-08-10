package org.txazo.pattern.structural.proxy.protect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxyTest {

	@Test
	public void testProxy() {
		Handler handler = new ProxyHandler();
		handler.handle("admin");
		handler.handle("txazo");
	}

}
