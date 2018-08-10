package org.txazo.java.encrypt.hmac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HMACUtilTest {

	@Test
	public void testSHA() throws Exception {
		String plainText = "784990655@qq.com";

		String hmacMD5Key = HMACUtil.initHmacKey(HMACUtil.HMAC_MD5);
		System.out.println("HMAC MD5密钥：" + hmacMD5Key);
		System.out.println("HMAC MD5加密后密文：" + HMACUtil.hmacMD5(hmacMD5Key, plainText));

		String hmacSHA1Key = HMACUtil.initHmacKey(HMACUtil.HMAC_SHA1);
		System.out.println("HMAC SHA1密钥：" + hmacSHA1Key);
		System.out.println("HMAC SHA1加密后密文：" + HMACUtil.hmacSHA1(hmacSHA1Key, plainText));
	}
}
