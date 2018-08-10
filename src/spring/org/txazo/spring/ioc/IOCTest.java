package org.txazo.spring.ioc;

import org.junit.Test;
import org.txazo.spring.ioc.service.IOCService;
import org.txazo.test.base.BaseTest;

/**
 * 模拟IOC
 * 
 * @author txazo
 *
 */
public class IOCTest extends BaseTest {

	@Test
	public void testIOC() {
		IOCXmlFactory factory = new IOCXmlFactory("/spring-ioc.xml");
		IOCService iocService = (IOCService) factory.getBean("iocService");
		iocService.show();
	}

}
