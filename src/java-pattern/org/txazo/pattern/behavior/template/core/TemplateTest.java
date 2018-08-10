package org.txazo.pattern.behavior.template.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TemplateTest {

	@Test
	public void testTemplate() throws Exception {
		AbstractTemplate abstractTemplate = new ConcreteTemplate();
		abstractTemplate.templateMethod();
	}

}
