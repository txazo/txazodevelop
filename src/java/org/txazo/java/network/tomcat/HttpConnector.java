package org.txazo.java.network.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

public class HttpConnector implements Runnable {

	private Stack<HttpProcessor> processors = new Stack<HttpProcessor>();

	private boolean stopped = false;
	private int curProcessors = 0;
	private int maxProcessors = 100;
	private ServerSocket serverSocket;
	private ServletContainer container;

	public synchronized void start() {
		try {
			serverSocket = new ServerSocket(80);
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		while (!stopped) {
			Socket socket = null;

			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				continue;
			}

			HttpProcessor processor = createProcessor();
			if (processor == null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
				continue;
			}

			processor.assign(socket);
		}
	}

	public HttpProcessor createProcessor() {
		synchronized (processors) {
			if (processors.size() > 0) {
				return processors.pop();
			}

			if (maxProcessors > 0 && curProcessors < maxProcessors) {
				return newProcessor();
			} else {
				if (maxProcessors < 0) {
					return newProcessor();
				} else {
					return null;
				}
			}
		}
	}

	public HttpProcessor newProcessor() {
		curProcessors++;
		HttpProcessor processor = new HttpProcessor(this);
		new Thread(processor).start();
		return processor;
	}

	public void recycle(HttpProcessor processor) {
		synchronized (processors) {
			processors.push(processor);
		}
	}

	public void stop() {
		stopped = true;

		try {
			serverSocket.close();
		} catch (IOException e) {
		}

		synchronized (processors) {
			for (HttpProcessor processor : processors) {
				processor.stop();
			}
		}
	}

	public ServletContainer getContainer() {
		return container;
	}

	public void setContainer(ServletContainer container) {
		this.container = container;
	}

}
