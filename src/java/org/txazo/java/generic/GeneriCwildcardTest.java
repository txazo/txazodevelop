package org.txazo.java.generic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 泛型 - 通配符
 * 
 * @author txazo
 * 
 */
public class GeneriCwildcardTest extends BaseTest {

	private static class Generic {

		/** 无限定通配符 */
		public static void cwildcard1(List<?> list) {
			logger.info("{}", list.getClass());
		}

		/** 上边界限定通配符 */
		public static void cwildcard2(List<? extends InputStream> list) {
			logger.info("{}", list.getClass());
		}

		/** 下边界限定通配符 */
		public static void cwildcard3(List<? super FileInputStream> list) {
			logger.info("{}", list.getClass());
		}

	}

	@Test
	public void testGeneriCwildcard() {
		Generic.cwildcard1(new ArrayList<String>());
		Generic.cwildcard2(new ArrayList<FileInputStream>());
		Generic.cwildcard3(new ArrayList<InputStream>());
	}

}
