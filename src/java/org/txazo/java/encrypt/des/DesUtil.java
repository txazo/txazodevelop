package org.txazo.java.encrypt.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtil {

	private static final String DES = "DES";

	private static Key generatorKey(String key) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(new BASE64Decoder().decodeBuffer(key));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		return secretKey;
	}

	/** DES加密 */
	public static String encrypt(String code, String key) throws Exception {
		Key k = generatorKey(key);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return new BASE64Encoder().encodeBuffer(cipher.doFinal(code.getBytes()));
	}

	/** DES解密 */
	public static String decrypt(String code, String key) throws Exception {
		Key k = generatorKey(key);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(code)));
	}

	public static String initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(DES);
		SecretKey secretKey = kg.generateKey();
		return new BASE64Encoder().encodeBuffer(secretKey.getEncoded());
	}

}
