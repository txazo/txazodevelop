package org.txazo.log.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Commons Logging
 * 
 * @author txazo
 * 
 */
public class LogTest {

	/**
	 * Commons Logging + java.util.logging
	 * 
	 * Commons Logging: commons-logging.jar
	 */
	@Test
	public void testJul() {
		Log logger = LogFactory.getLog(this.getClass());
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

	/**
	 * Commons Logging + Log4j
	 * 
	 * <pre>
	 * Commons Logging: commons-logging.jar
	 * Log4j: log4j.jar
	 * Config: log4j.properties
	 * </pre>
	 */
	@Test
	public void testLog4j() {
		Log logger = LogFactory.getLog("stdout");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

}
