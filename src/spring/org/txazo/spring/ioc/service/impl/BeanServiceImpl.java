package org.txazo.spring.ioc.service.impl;

import org.txazo.spring.ioc.service.BeanService;

public class BeanServiceImpl implements BeanService {

	@Override
	public String getBeanName() {
		return "IOC";
	}

}
