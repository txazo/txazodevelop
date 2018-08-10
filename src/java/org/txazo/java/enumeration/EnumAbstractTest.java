package org.txazo.java.enumeration;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 带抽象方法的枚举
 */
public class EnumAbstractTest extends BaseTest {

	private enum Color {

		RED {
			@Override
			public String getName() {
				return "red";
			}
		},
		GREEN {
			@Override
			public String getName() {
				return "green";
			}
		},
		BLANK {
			@Override
			public String getName() {
				return "blank";
			}
		},
		YELLOW {
			@Override
			public String getName() {
				return "yellow";
			}
		};

		public abstract String getName();

	}

	@Test
	public void testEnumAbstract() {
		logger.info("{}", Color.RED.getName());
		logger.info("{}", Color.GREEN.getName());
		logger.info("{}", Color.BLANK.getName());
		logger.info("{}", Color.YELLOW.getName());
	}

}
