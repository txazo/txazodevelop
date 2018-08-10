package org.txazo.java.enumeration;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 枚举实现接口
 */
public class EnumImplementTest extends BaseTest {

	private interface Display {

		public void display();

	}

	private enum Color implements Display {

		RED(1, "red"), GREEN(2, "green"), BLANK(3, "blank"), YELLOW(4, "yellow");

		private int index;
		private String name;

		private Color(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public void display() {
			logger.info("index[{}], name[{}]", index, name);
		}

	}

	@Test
	public void testEnumImplement() {
		Color.RED.display();
		Color.GREEN.display();
		Color.BLANK.display();
		Color.YELLOW.display();
	}

}
