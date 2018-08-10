package org.txazo.java.encrypt.hmac;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class HMACUtil {

	public static final String HMAC_MD5 = "HmacMD5";
	public static final String HMAC_SHA1 = "HmacSHA1";

	public static String initHmacKey(String algorithm) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		SecretKey secretKey = keyGenerator.generateKey();
		return new BASE64Encoder().encodeBuffer(secretKey.getEncoded());
	}

	public static String hmac(String algorithm, String key, String data) throws Exception {
		byte[] keys = new BASE64Decoder().decodeBuffer(key);
		SecretKey secretKey = new SecretKeySpec(keys, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(secretKey);
		return new BASE64Encoder().encodeBuffer(mac.doFinal(data.getBytes()));
	}

	public static String hmacMD5(String key, String data) throws Exception {
		return hmac(HMAC_MD5, key, data);
	}

	public static String hmacSHA1(String key, String data) throws Exception {
		return hmac(HMAC_SHA1, key, data);
	}

}
