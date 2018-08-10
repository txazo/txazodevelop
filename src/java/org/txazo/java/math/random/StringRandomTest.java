package org.txazo.java.math.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符串随机数
 * 
 * @author txazo
 * 
 */
public class StringRandomTest extends BaseTest {

	private static String str = "abcdefghijklmnopqrstuvwxyz0123456789";

	@Test
	public void testStringRandom() {
		/** Ascii码随机字符串 */
		System.out.println(RandomStringUtils.randomAscii(6));
		/** 数字随机字符串 */
		System.out.println(RandomStringUtils.randomNumeric(6));
		/** 字母随机字符串 */
		System.out.println(RandomStringUtils.randomAlphabetic(6));
		/** 字母数字随机字符串 */
		System.out.println(RandomStringUtils.randomAlphanumeric(6));
		/** 自定义随机字符串 */
		System.out.println(RandomStringUtils.random(6, str));
	}

}
