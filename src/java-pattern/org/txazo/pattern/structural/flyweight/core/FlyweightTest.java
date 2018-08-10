package org.txazo.pattern.structural.flyweight.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FlyweightTest {

	@Test
	public void testFlyweight() {
		FlyweightFactory factory = FlyweightFactory.getInstance();
		Flyweight flyweight = factory.getFlyweight("txazo");
		flyweight.operation("1");
		flyweight = factory.getFlyweight("txazo");
		flyweight.operation("2");
	}

}
