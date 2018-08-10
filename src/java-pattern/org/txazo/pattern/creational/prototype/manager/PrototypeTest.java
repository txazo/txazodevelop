package org.txazo.pattern.creational.prototype.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrototypeTest {

	@Test
	public void testPrototype() throws Exception {
		ConcretePrototype prototype = new ConcretePrototype();
		prototype.setName("txazo");
		PrototypeManager.setPrototype("ConcretePrototype", prototype);
		ConcretePrototype clone = (ConcretePrototype) PrototypeManager
				.getClone("ConcretePrototype");
		System.out.println(clone.getName());
	}

}
