package org.txazo.java.network.tomcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProcessor implements Runnable {

	protected static Logger logger = LoggerFactory.getLogger(HttpProcessor.class);

	private boolean available = false;
	private boolean stopped = false;
	private Socket socket;
	private HttpConnector connector;
	private HttpRequest request;
	private HttpResponse response;

	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}

	@Override
	public void run() {
		while (!stopped) {
			Socket socket = await();
			if (socket == null) {
				continue;
			}

			try {
				process(socket);
			} catch (IOException e) {
			}

			connector.recycle(this);
		}
	}

	public synchronized void assign(Socket socket) {
		while (available) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		this.socket = socket;
		available = true;
		notifyAll();
	}

	public synchronized Socket await() {
		while (!available) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Socket socket = this.socket;
		available = false;
		notifyAll();

		return socket;
	}

	private void process(Socket socket) throws IOException {
		parseConnection(socket);
		parseRequest();
		parseHeaders();

		connector.getContainer().invoke(request, response);
	}

	private void parseConnection(Socket socket) throws IOException {
		request = new HttpRequest();
		request.setInput(socket.getInputStream());

		response = new HttpResponse();
		response.setOutput(socket.getOutputStream());
		response.setSocket(socket);
	}

	private void parseRequest() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInput()));
		logger.info(br.readLine());
	}

	private void parseHeaders() {
	}

	public void stop() {
		stopped = true;
	}

}
