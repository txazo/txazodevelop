package org.txazo.pattern.creational.builder.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DirectorTest {

	@Test
	public void testDirector() {
		Builder builderA = new ConcreteBuilderA();
		Director directorA = new Director(builderA);
		directorA.build();
		directorA.getProduct();

		Builder builderB = new ConcreteBuilderB();
		Director directorB = new Director(builderB);
		directorB.build();
		directorB.getProduct();
	}

}
