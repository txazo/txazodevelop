package org.txazo.pattern.creational.prototype.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrototypeTest {

	@Test
	public void testPrototype() throws Exception {
		ConcretePrototype prototype = new ConcretePrototype("txazo", 24);
		ConcretePrototype clonePrototype = (ConcretePrototype) prototype
				.clone();
		System.out.println(clonePrototype.getUsernameString());
		System.out.println(clonePrototype.getAge());
	}

}
