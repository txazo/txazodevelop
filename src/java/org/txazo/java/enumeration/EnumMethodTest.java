package org.txazo.java.enumeration;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 枚举方法
 */
public class EnumMethodTest extends BaseTest {

	private enum Color {

		RED(1, "red"), GREEN(2, "green"), BLANK(3, "blank"), YELLOW(4, "yellow");

		private int index;
		private String name;

		private Color(int index, String name) {
			this.index = index;
			this.name = name;
		}

		public static String getName(int index) {
			/** 枚举遍历 */
			for (Color color : Color.values()) {
				if (index == color.getIndex()) {
					return color.getName();
				}
			}
			return null;
		}

		public int getIndex() {
			return index;
		}

		public String getName() {
			return name;
		}

	}

	@Test
	public void testEnumField() {
		Color color = Color.RED;
		logger.info("index[{}], name[{}]", color.getIndex(), color.getName());
		logger.info("name[{}]", Color.getName(2));
	}

}
