package org.txazo.pattern.structural.facade.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FacadeTest {

	@Test
	public void testFacade() {
		Facade facade = new Facade();
		facade.service();
	}

}
