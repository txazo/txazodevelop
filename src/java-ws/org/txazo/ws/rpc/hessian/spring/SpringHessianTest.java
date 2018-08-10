package org.txazo.ws.rpc.hessian.spring;

import javax.annotation.Resource;

import org.junit.Test;
import org.txazo.test.base.SpringBaseTest;

/**
 * Spring Hessian
 * 
 * @author txazo
 * 
 */
public class SpringHessianTest extends SpringBaseTest {

	@Resource
	private HessianService springHessianService;

	@Test
	public void testSpringHessian() {
		for (int i = 0; i < 10; i++) {
			logger.info("{}", springHessianService.getRemoteTime());
		}
	}

}
