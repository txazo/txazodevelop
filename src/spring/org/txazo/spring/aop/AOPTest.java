package org.txazo.spring.aop;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 模拟AOP
 * 
 * @author txazo
 *
 */
public class AOPTest extends BaseTest {

	@Test
	public void testAOP() {
		AOPXmlFactory factory = new AOPXmlFactory("/spring-aop.xml");
	}

}
