package org.txazo.pattern.creational.simplefactory.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleFactoryTest {

	@Test
	public void testSimpleFactory() {
		Fruit apple = FruitFactory.createFruit(FruitFactory.FRUIT_APPLE);
		System.out.println(apple.getName());

		Fruit orange = FruitFactory.createFruit(FruitFactory.FRUIT_ORANGE);
		System.out.println(orange.getName());
	}

}
