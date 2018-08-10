package org.txazo.java.encrypt.sha;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SHATest {

	@Test
	public void testSHA() throws Exception {
		String plainText = "784990655@qq.com";

		System.out.println(SHAUtil.sha(plainText));
		System.out.println(SHAUtil.sha1(plainText));

		/** Apache Commons Codec框架 */
		System.out.println(DigestUtils.sha1Hex(plainText));
	}

}
