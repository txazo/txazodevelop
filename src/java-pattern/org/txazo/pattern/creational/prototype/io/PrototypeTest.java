package org.txazo.pattern.creational.prototype.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrototypeTest {

	@Test
	public void testPrototype() throws Exception {
		ConcretePrototype prototype = new ConcretePrototype();
		prototype.setName("txazo");
		ConcretePrototype clone = (ConcretePrototype) prototype.clone();
		System.out.println(clone.getName());
	}

}
