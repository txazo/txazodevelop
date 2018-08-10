package org.txazo.java.jsp.etag;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * ETagOutputStream
 * 
 * @author txazo
 * @since 2014-03-03
 * 
 */
public class ETagOutputStream extends ServletOutputStream {

	private OutputStream os = null;

	public ETagOutputStream(OutputStream os) {
		super();
		this.os = os;
	}

	@Override
	public void write(int b) throws IOException {
		os.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		os.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		os.write(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		os.flush();
	}

	@Override
	public void close() throws IOException {
		os.close();
	}

}
