package org.txazo.pattern.structural.adapter.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Adaptee {

	private static Logger logger = LoggerFactory.getLogger("stdout");

	public void specificRequest() {
		logger.info("Adaptee specificRequest");
	}

}
