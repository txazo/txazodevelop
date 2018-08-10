package org.txazo.pattern.creational.builder.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DirectorTest {

	@Test
	public void testDirector() {
		ProductBuilder builder = new ProductBuilder(1000, "txazo", 20.0);
		Product product = builder.setPersonName("txazo").setCompanyName("txazo").build();
		product.product();
	}

}
