package org.txazo.java.encrypt.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static final String MD5 = "MD5";

	/** 16位MD5字符串加密 */
	public static String md5Hex(String plainText) {
		return md5(plainText).substring(8, 24);
	}

	/** 16位MD5字节数组加密 */
	public static String md5Hex(byte[] bytes) {
		return md5(bytes).substring(8, 24);
	}

	/** 32位MD5字符串加密 */
	public static String md5(String plainText) {
		return md5(plainText.getBytes());
	}

	/*** 32位MD5字节数组加密 */
	public static String md5(byte[] bytes) {
		String cipherText = null;
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			md.update(bytes);
			byte[] data = md.digest();
			cipherText = byteToHexString(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	/** 字节数组转换为16进制字符串 */
	public static String byteToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(data[i] & 0xFF);
			if (hex.length() == 1) {
				sb.append("0").append(hex);
			} else {
				sb.append(hex);
			}
		}
		return sb.toString();
	}

}