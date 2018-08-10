package org.txazo.pattern.creational.prototype.deepclone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrototypeTest {

	@Test
	public void testPrototype() throws Exception {
		Prototype prototype = new Prototype();
		prototype.setId(1000);
		prototype.setName("txazo");
		PrototypeHolder prototypeHolder = new PrototypeHolder();
		prototypeHolder.setId(1001);
		prototypeHolder.setPrototype(prototype);

		PrototypeHolder clone = (PrototypeHolder) prototypeHolder.clone();
		prototypeHolder = null;
		prototype = null;

		System.out.println(clone.getId());
		System.out.println(clone.getPrototype().getId());
		System.out.println(clone.getPrototype().getName());
	}
	
}
