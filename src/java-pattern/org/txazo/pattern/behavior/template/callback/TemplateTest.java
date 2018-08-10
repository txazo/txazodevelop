package org.txazo.pattern.behavior.template.callback;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TemplateTest {

	@Test
	public void testTemplate() throws Exception {
		Template template = new Template();
		template.templateMethod(new TemplateCallback() {

			@Override
			public void abstractMethod() {
				System.out.println("abstractMethod");
			}

		});
	}

}
