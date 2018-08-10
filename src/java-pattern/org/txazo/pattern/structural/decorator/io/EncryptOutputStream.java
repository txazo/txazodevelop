package org.txazo.pattern.structural.decorator.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(int b) throws IOException {
		super.write(encryptOut(b));
	}

	/** 加密 */
	private int encryptOut(int b) {
		return EncryptUtil.encryptOut(b);
	}

}
