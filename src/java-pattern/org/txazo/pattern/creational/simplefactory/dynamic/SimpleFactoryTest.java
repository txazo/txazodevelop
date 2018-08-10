package org.txazo.pattern.creational.simplefactory.dynamic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleFactoryTest {

	@Test
	public void testSimpleFactory() throws Exception {
		Fruit fruit = FruitFactory.createFruit();
		System.out.println(fruit.getName());
	}

}
