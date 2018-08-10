package org.txazo.pattern.structural.proxy.virtual;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxyTest {

	@Test
	public void testProxy() {
		Processor processor = new ProxyProcessor();
		processor.process();
	}

}
