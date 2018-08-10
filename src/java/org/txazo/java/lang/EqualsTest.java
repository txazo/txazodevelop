package org.txazo.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Object.equals()
 */
public class EqualsTest extends BaseTest {

	@Test
	public void testEquals() {
		Equal1 e11 = new Equal1();
		Equal1 e12 = new Equal1();
		/** 未覆盖Object.equals()方法，判断是否同一个对象 */
		Assert.assertFalse(e11.equals(e12));

		Equal2 e21 = new Equal2("Equal");
		Equal2 e22 = new Equal2("Equal");
		/** 覆盖Object.equals()方法， */
		Assert.assertTrue(e21.equals(e22));
	}

	private class Equal1 {
	}

	private class Equal2 {

		private String name;

		public Equal2(String name) {
			this.name = name;
		}

		/**
		 * 重写equals方法，比较对象内部实例变量是否相等
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Equal2 other = (Equal2) obj;
			if (name == null) {
				return other.name == null;
			}
			return name.equals(other.name);
		}

	}

}
