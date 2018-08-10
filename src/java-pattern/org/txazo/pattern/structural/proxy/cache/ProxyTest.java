package org.txazo.pattern.structural.proxy.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProxyTest {

	@Test
	public void testProxy() {
		InfoDao infoDao = new InfoDaoProxy();
		infoDao.getInfoById(Long.valueOf(1));
		infoDao.getInfoById(Long.valueOf(1));
		infoDao.getInfoById(Long.valueOf(2));
		infoDao.getInfoById(Long.valueOf(2));
	}

}
