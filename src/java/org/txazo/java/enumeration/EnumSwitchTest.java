package org.txazo.java.enumeration;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 枚举 Switch
 */
public class EnumSwitchTest extends BaseTest {

	private enum Color {

		RED, GREEN, BLANK, YELLOW

	}

	@Test
	public void testEnumSwitch() {
		Color color = Color.RED;
		switch (color) {
		case RED:
			logger.info("red");
			break;
		case GREEN:
			logger.info("green");
			break;
		case BLANK:
			logger.info("blank");
			break;
		case YELLOW:
			logger.info("yellow");
			break;
		default:
			logger.info("no color");
			break;
		}
	}

}
