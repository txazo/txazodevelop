package org.txazo.pattern.structural.decorator.io;

public class EncryptUtil {

	public static int encryptIn(int code) {
		return code & 0x0F;
	}

	public static int encryptOut(int code) {
		return code & 0x8F;
	}

}