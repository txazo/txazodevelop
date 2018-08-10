package org.txazo.log.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * java.util.logging
 * 
 * @author txazo
 * 
 */
public class LogTest {

	@Test
	public void testLog() {
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.log(Level.INFO, "info");
		logger.log(Level.WARNING, "warn");
		logger.log(Level.SEVERE, "error");
	}

}
