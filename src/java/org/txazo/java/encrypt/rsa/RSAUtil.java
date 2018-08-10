package org.txazo.java.encrypt.rsa;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {

	private static final String RSA = "RSA";
	public static final String PUBLIC_KEY = "PUBLIC_KEY";
	public static final String PRIVATE_KEY = "PRIVATE_KEY";

	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/** RSA加密 */
	public static String encrypt(String text, String key) throws Exception {
		PublicKey publicKey = getPublicKey(key);
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] enBytes = cipher.doFinal(text.getBytes());
		return new BASE64Encoder().encodeBuffer(enBytes);
	}

	/** RSA解密 */
	public static String decrypt(String text, String key) throws Exception {
		PrivateKey privateKey = getPrivateKey(key);
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] deBytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(text));
		return new String(deBytes);
	}

	private static String getKey(Key key) throws Exception {
		return new BASE64Encoder().encode(key.getEncoded());
	}

	public static Map<String, String> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		String publicKey = getKey(keyPair.getPublic());
		String privateKey = getKey(keyPair.getPrivate());

		Map<String, String> keyMap = new HashMap<String, String>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}

}
