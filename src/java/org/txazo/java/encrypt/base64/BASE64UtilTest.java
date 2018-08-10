package org.txazo.java.encrypt.base64;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BASE64UtilTest {

	@Test
	public void testBASE64() throws IOException {
		String plainText = "784990655@qq.com";
		String encodeText = BASE64Util.encode(plainText);
		String decodeText = BASE64Util.decode(encodeText);

		System.out.println("明文：" + plainText);
		System.out.println("加密后密文：" + encodeText);
		System.out.println("解密后明文：" + decodeText);
	}

}
