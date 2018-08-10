package org.txazo.pattern.structural.decorator.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EncryptInputStream extends FilterInputStream {

	protected EncryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		return encryptIn(super.read());
	}

	/** 解密 */
	private int encryptIn(int b) {
		return EncryptUtil.encryptIn(b);
	}

}
