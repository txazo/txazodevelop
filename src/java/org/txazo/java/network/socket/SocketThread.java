package org.txazo.java.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketThread implements Runnable {

	protected static Logger logger = LoggerFactory.getLogger("stdout");

	private boolean isRunning = true;
	private Socket socket;
	private BufferedReader input;
	private PrintStream out;

	public SocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			out = new PrintStream(socket.getOutputStream(), true, "UTF-8");

			while (isRunning) {
				String data = input.readLine();

				logger.info("Server receive: {}", data);

				if (data.equals("connect")) {
					out.println("connect ok");
				} else if (data.equals("send")) {
					out.println("send ok");
				} else if (data.equals("close")) {
					out.println("close ok");
					isRunning = false;
				}
			}

			input.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
