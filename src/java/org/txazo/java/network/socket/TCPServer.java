package org.txazo.java.network.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPServer {

	protected static Logger logger = LoggerFactory.getLogger("stdout");

	private boolean isRunning = true;
	private ServerSocket server;

	public TCPServer() {
		try {
			server = new ServerSocket(10101);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		logger.info("Server start...");

		while (isRunning) {
			Socket socket = server.accept();

			logger.info("Server accept...");

			new Thread(new SocketThread(socket)).start();
		}

		server.close();
	}

	public void stop() {
		isRunning = false;
	}

	public static void main(String[] args) throws IOException {
		new TCPServer().start();
	}

}
