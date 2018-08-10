package org.txazo.java.encrypt.des;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DesUtilTest {

	@Test
	public void testDES() throws Exception {
		String plainText = "784990655@qq.com";
		System.out.println("明文：" + plainText);

		String key = DesUtil.initKey();
		System.out.println("密钥：" + key);

		String encryptText = DesUtil.encrypt(plainText, key);
		System.out.println("加密后密文：" + encryptText);

		String decryptText = DesUtil.decrypt(encryptText, key);
		System.out.println("解密后明文：" + new String(decryptText));
	}

}
