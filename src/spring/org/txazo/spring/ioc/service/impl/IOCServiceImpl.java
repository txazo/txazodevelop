package org.txazo.spring.ioc.service.impl;

import org.txazo.spring.ioc.service.BeanService;
import org.txazo.spring.ioc.service.IOCService;
import org.txazo.test.base.BaseTest;

public class IOCServiceImpl extends BaseTest implements IOCService {

	private String user;
	private BeanService beanService;

	@Override
	public void show() {
		logger.info("{} {}", user, beanService.getBeanName());
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setBeanService(BeanService beanService) {
		this.beanService = beanService;
	}

}
