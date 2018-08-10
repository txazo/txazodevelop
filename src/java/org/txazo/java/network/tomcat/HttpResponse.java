package org.txazo.java.network.tomcat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpResponse {

	private Socket socket;
	private OutputStream output;

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void close() {
		try {
			if (output != null) {
				output.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
