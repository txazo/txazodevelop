package org.txazo.log.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slf4j
 * 
 * @author txazo
 * 
 */
public class LogTest {

	/**
	 * Slf4j + Log4j
	 * 
	 * <pre>
	 * Slf4j: slf4j-api.jar, slf4j-log4j.jar
	 * Log4j: log4j.jar
	 * Config: log4j.properties
	 * </pre>
	 */
	@Test
	public void testLog4j() {
		Logger logger = LoggerFactory.getLogger("stdout");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

	/**
	 * Slf4j + Commons Logging + Log4j
	 * 
	 * <pre>
	 * Slf4j: slf4j-api.jar, slf4j-jcl.jar
	 * Commons Logging: commons-logging.jar
	 * Log4j: log4j.jar
	 * Config: log4j.properties
	 * </pre>
	 */
	@Test
	public void testJclLog4j() {
		Logger logger = LoggerFactory.getLogger("stdout");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

	/**
	 * Slf4j + Logback
	 * 
	 * <pre>
	 * Slf4j: slf4j-api.jar
	 * Logback: logback-access.jar, logback-classic.jar, logback-core.jar
	 * Config: logback.xml
	 * </pre>
	 */
	@Test
	public void testLogback() {
		Logger logger = LoggerFactory.getLogger("stdout");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}

}
