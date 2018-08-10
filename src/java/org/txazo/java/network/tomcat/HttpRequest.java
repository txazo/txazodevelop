package org.txazo.java.network.tomcat;

import java.io.IOException;
import java.io.InputStream;

public class HttpRequest {

	private InputStream input;

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public void close() {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
		}
	}

}
