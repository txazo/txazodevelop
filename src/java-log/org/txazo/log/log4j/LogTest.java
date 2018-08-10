package org.txazo.log.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Log4j
 * 
 * @author txazo
 * 
 */
public class LogTest {

	/**
	 * Log4j
	 * 
	 * <pre>
	 * Log4j: log4j.jar
	 * Config: log4j.properties
	 * </pre>
	 */
	@Test
	public void testLog() {
		Logger logger = Logger.getLogger("stdout");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

}
