package org.txazo.java.encrypt.rsa;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RSAUtilTest {

	@Test
	public void testRSA() throws Exception {
		Map<String, String> keyMap = RSAUtil.initKey();
		String publicKey = keyMap.get(RSAUtil.PUBLIC_KEY);
		String privateKey = keyMap.get(RSAUtil.PRIVATE_KEY);

		String plainText = "784990655@qq.com";
		System.out.println("明文" + plainText);
		System.out.println("公钥" + publicKey);
		System.out.println("私钥" + privateKey);

		String encryptText = RSAUtil.encrypt(plainText, publicKey);
		System.out.println("加密后密文" + encryptText);

		String decryptText = RSAUtil.decrypt(encryptText, privateKey);
		System.out.println("解密后明文" + decryptText);
	}

}
