package org.txazo.java.generic;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class GenericExtendsTest extends BaseTest {

	private static class Generic {

		public static <T extends Object> void generic(T t) {
			logger.info("{}", ((Object) t).getClass());
		}

	}

	@Test
	public void testGenericExtends() {
		Generic.generic("txazo");
	}

}
