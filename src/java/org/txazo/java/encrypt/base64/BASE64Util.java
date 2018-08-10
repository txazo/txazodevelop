package org.txazo.java.encrypt.base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64Util {

	/**
	 * Base64加密
	 * 
	 * <pre>
	 * 1. Base64加密后的字节数是8的倍数，不够位数以=填充
	 * </pre>
	 * 
	 */
	public static String encode(String code) {
		return encode(code.getBytes());
	}

	public static String encode(byte[] code) {
		return new BASE64Encoder().encodeBuffer(code);
	}

	/** Base64解密 */
	public static String decode(String code) throws IOException {
		return new String(new BASE64Decoder().decodeBuffer(code));
	}

}
