package org.txazo.java.enumeration;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 枚举常量
 */
public class EnumConstantTest extends BaseTest {

	private enum Color {

		RED, GREEN, BLANK, YELLOW;

		public static Color index(int index) {
			if (index < 0 || index > values().length) {
				throw new ArrayIndexOutOfBoundsException("");
			}
			return values()[index];
		}

	}

	@Test
	public void testEnumConstant() {
		logger.info("{}", Color.RED);
		logger.info("{}", Color.GREEN.ordinal());
		logger.info("{}", Color.index(2));
		logger.info("{}", Color.values().length);
		logger.info("{}", Color.valueOf("YELLOW"));
		logger.info("{}", Enum.valueOf(Color.class, "YELLOW"));
	}

}
