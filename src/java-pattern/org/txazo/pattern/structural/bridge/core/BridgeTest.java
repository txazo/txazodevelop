package org.txazo.pattern.structural.bridge.core;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class BridgeTest extends BaseTest {

	@Test
	public void testBridge() {
		Implementor implementorA = new AImplementor();
		Implementor implementorB = new BImplementor();

		Abstraction abstraction1 = new AAbstraction(implementorA);
		abstraction1.operation();
		abstraction1.setImplementor(implementorB);
		abstraction1.operation();

		Abstraction abstraction2 = new BAbstraction(implementorA);
		abstraction2.operation();
		abstraction2.setImplementor(implementorB);
		abstraction2.operation();
	}

}
