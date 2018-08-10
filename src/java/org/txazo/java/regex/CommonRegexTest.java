package org.txazo.java.regex;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 常用正则表达式
 */
public class CommonRegexTest extends BaseTest {

	private void testRegexMatches(String regex, String input) {
		Assert.assertTrue(Pattern.compile(regex).matcher(input).matches());
	}

	@Test
	public void testCommonRegex() {
		/** 整数 */
		testRegexMatches("^[0-9]*$", "1234");
		testRegexMatches("^\\d*$", "1234");

		/** 整数或小数 */
		testRegexMatches("^\\d+\\.?\\d*$", "12.34");

		/** 用户名 */

		/** 密码(字母开头, 只能包含字母数字和下划线, 长度6~18) */
		testRegexMatches("^[a-zA-Z]\\w{5,17}$", "txazo2015");

		/** 邮箱 */
		testRegexMatches("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?", "txazo1218@163.com");
	}

}
