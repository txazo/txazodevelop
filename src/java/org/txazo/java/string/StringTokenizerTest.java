package org.txazo.java.string;

import java.util.StringTokenizer;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.StringTokenizer
 */
public class StringTokenizerTest extends BaseTest {

	/**
	 * StringTokenizer, 字符串分隔
	 */
	@Test
	public void testStringTokenizer() {
		String str = "1|2|3|4|5";
		StringTokenizer st = new StringTokenizer(str, "|");
		while (st.hasMoreTokens()) {
			logger.info(st.nextToken());
		}
	}

}
