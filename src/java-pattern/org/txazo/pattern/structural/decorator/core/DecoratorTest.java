package org.txazo.pattern.structural.decorator.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DecoratorTest {

	@Test
	public void testDecorator() {
		Component compnoent = new ConcreteComponent();
		Component compnoent1 = new ConcreteDecoratorB(compnoent);
		Component compnoent2 = new ConcreteDecoratorA(compnoent1);
		compnoent2.operation();
	}

}
