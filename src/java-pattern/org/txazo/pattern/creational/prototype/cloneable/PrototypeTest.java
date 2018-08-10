package org.txazo.pattern.creational.prototype.cloneable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrototypeTest {

	@Test
	public void testPrototype() throws Exception {
		Prototype prototype = new Prototype();
		prototype.setId(1000);
		Prototype clone = (Prototype) prototype.clone();
		System.out.println(clone.getId());
	}

}
