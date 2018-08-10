package org.txazo.java.encrypt.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class SHAUtil {

	private static final String SHA = "SHA";
	private static final String SHA1 = "SHA1";

	/** SHA加密 */
	private static String sha(String algorithm, String code) {
		byte[] encodeBytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(code.getBytes());
			encodeBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BASE64Encoder().encodeBuffer(encodeBytes);
	}

	/** SHA加密 */
	public static String sha(String code) {
		return sha(SHA, code);
	}

	/** SHA1加密 */
	public static String sha1(String code) {
		return sha(SHA1, code);
	}

}
