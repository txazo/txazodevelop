package org.txazo.ws.rpc.hessian.client;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessianTest extends BaseTest {

	@Test
	public void testHessian() throws Exception {
		String remoteUrl = "http://127.0.0.1/servlet/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianService hessianService = (HessianService) factory.create(HessianService.class, remoteUrl);
		for (int i = 0; i < 10; i++) {
			logger.info("{}", hessianService.getRemoteTime());
		}
	}

}
