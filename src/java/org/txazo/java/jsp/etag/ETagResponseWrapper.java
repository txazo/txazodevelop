package org.txazo.java.jsp.etag;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * ETagResponseWrapper
 * 
 * @author txazo
 * @since 2014-03-03
 * 
 */
public class ETagResponseWrapper extends HttpServletResponseWrapper {

	private ServletOutputStream servletOutputStream = null;
	private PrintWriter printWriter = null;

	public ETagResponseWrapper(HttpServletResponse response, OutputStream buffer) {
		super(response);
		servletOutputStream = new ETagOutputStream(buffer);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return servletOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (printWriter == null) {
			printWriter = new PrintWriter(servletOutputStream);
		}
		return printWriter;
	}

	@Override
	public void flushBuffer() throws IOException {
		servletOutputStream.flush();
		if (printWriter != null) {
			printWriter.flush();
		}
	}

}
