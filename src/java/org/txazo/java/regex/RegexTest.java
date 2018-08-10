package org.txazo.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java正则表达式
 */
public class RegexTest extends BaseTest {

	private void testRegexMatches(String regex, String input) {
		Assert.assertTrue(Pattern.compile(regex).matcher(input).matches());
	}

	/**
	 * . 通配符
	 */
	@Test
	public void testDot() {
		testRegexMatches(".{1,3}", "123");
	}

	/**
	 * ^ $
	 */
	@Test
	public void testBeginEnd() {
		testRegexMatches("^txazo", "txazo");
		testRegexMatches("\\^txazo", "^txazo");
		testRegexMatches("txazo$", "txazo");
		testRegexMatches("txazo\\$", "txazo$");
	}

	/**
	 * <pre>
	 * \	\\
	 * n	\n		n:^$()[]{}.?+*
	 * </pre>
	 */
	@Test
	public void testBackslash() {
		testRegexMatches("t\\\\x", "t\\x");
		testRegexMatches("t\\(x\\)", "t(x)");
		testRegexMatches("t\\tx", "t\tx");
	}

	/**
	 * <pre>
	 * ?		0或1次
	 * + 		1次以上
	 * * 		0次以上
	 * {n}		n次
	 * {n,}		n次以上
	 * {m,n}	m到n次
	 * </pre>
	 */
	@Test
	public void testMatchCount() {
		testRegexMatches("\\d?", "1");
		testRegexMatches("\\d+", "123");
		testRegexMatches("\\d*", "123");
		testRegexMatches("\\d{3}", "123");
		testRegexMatches("\\d{3,}", "1234");
		testRegexMatches("\\d{3,5}", "1234");
	}

	/**
	 * ?? +? *? {n}? {n,}? {m,n}?
	 */
	@Test
	public void testMaxMatchCount() {
		Matcher matcher = Pattern.compile("([\\d]+)").matcher("1234");
		if (matcher.find()) {
			Assert.assertEquals("1234", matcher.group(1));
		}
		matcher = Pattern.compile("([\\d]+?)").matcher("1234");
		if (matcher.find()) {
			Assert.assertEquals("1", matcher.group(1));
		}
	}

	/**
	 * () (?:) (?=) (?!)
	 */
	@Test
	public void testSmallBracket() {
		testRegexMatches("\\(a\\)", "(a)");
		Matcher matcher = Pattern.compile("(\\d+)(\\w+)").matcher("1234abcd");
		if (matcher.find()) {
			logger.info("{}\t{}", matcher.group(1), matcher.group(2));
		}

		matcher = Pattern.compile("(?:\\d+)(\\w+)").matcher("1234abcd");
		if (matcher.find()) {
			logger.info("{}", matcher.group(1));
		}
	}

	/**
	 * | 或匹配, ()内使用
	 */
	@Test
	public void testOr() {
		testRegexMatches("t(x|xa|xaz)o", "txo");
		testRegexMatches("t(x|xa|xaz)o", "txao");
		testRegexMatches("t(x|xa|xaz)o", "txazo");
	}

	/**
	 * [] 字符集, 匹配包含的任一字符
	 */
	@Test
	public void testMiddleBracket() {
		testRegexMatches("\\[a\\]", "[a]");
		testRegexMatches("[abc]+", "a");
		testRegexMatches("[^abc]+", "1");
		testRegexMatches("[a-z]+", "abc");
		testRegexMatches("[^a-z]+", "123");
		testRegexMatches("[a-zA-Z]+", "aBc");
		testRegexMatches("[a-z[ABC]]+", "abcABC");
		testRegexMatches("[a-z[^a-z]]+", "abcABC123");
	}

	/**
	 * { }
	 */
	@Test
	public void testBigBracket() {
		testRegexMatches("[0-9]{3}", "123");
		testRegexMatches("\\{[0-9]{3}\\}", "{123}");
	}

	/**
	 * <pre>
	 * \b
	 * \B
	 * \d	[0-9]
	 * \D	[^0-9]
	 * \s	[\f\n\r\t\v]
	 * \S	[^\f\n\r\t\v]
	 * \w	[a-zA-Z0-9_]
	 * \W	[^a-zA-Z0-9_]
	 * </pre>
	 */
	@Test
	public void testEscape() {
		testRegexMatches("tx \\bzo\\b", "tx zo");
		testRegexMatches("tx\\Bzo", "txzo");
		testRegexMatches("\\d+", "1234");
		testRegexMatches("\\D+", "abcd");
		testRegexMatches("\\s+", " ");
		testRegexMatches("\\S+", "abcd");
		testRegexMatches("\\w+", "ab_12");
		testRegexMatches("\\W+", "!@#$");
	}

}
