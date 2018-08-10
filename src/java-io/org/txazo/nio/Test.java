package org.txazo.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Test {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 9999);
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(1);
		outputStream.flush();
		outputStream.close();
		socket.close();
	}

}
