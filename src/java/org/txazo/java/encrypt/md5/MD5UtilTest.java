package org.txazo.java.encrypt.md5;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class MD5UtilTest extends BaseTest {

	@Test
	public void testMD5() throws Exception {
		String plainText = "784990655@qq.com";

		logger.info(MD5Util.md5(plainText));
		logger.info(MD5Util.md5Hex(plainText));

		/** Apache Commons Codec框架 */
		logger.info(DigestUtils.md5Hex(plainText));
	}

}
