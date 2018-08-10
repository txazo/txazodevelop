package org.txazo.java.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPClient {

	protected static Logger logger = LoggerFactory.getLogger("stdout");

	private boolean isRunning = true;
	private Socket client;
	private BufferedReader input;
	private PrintStream out;

	public void connect() throws IOException {
		client = new Socket("127.0.0.1", 10101);

		logger.info("Client connect...");

		input = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
		out = new PrintStream(client.getOutputStream(), true, "UTF-8");

		out.println("connect");

		while (isRunning) {
			String data = input.readLine();

			logger.info("Client receive: {}", data);

			if (data.equals("connect ok")) {
				out.println("send");
			} else if (data.equals("send ok")) {
				out.println("close");
			} else if (data.equals("close ok")) {
				isRunning = false;
			}
		}

		input.close();
		out.close();
		client.close();
	}

	public static void main(String[] args) throws IOException {
		new TCPClient().connect();
	}

}
