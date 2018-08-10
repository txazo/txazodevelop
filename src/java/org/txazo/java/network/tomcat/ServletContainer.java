package org.txazo.java.network.tomcat;

import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServletContainer {

	private int poolSize = 100;
	private ExecutorService servletPool = null;

	public ServletContainer() {
		servletPool = Executors.newFixedThreadPool(poolSize);
	}

	public void invoke(HttpRequest request, HttpResponse response) {
		servletPool.execute(new ServletHandlerThread(request, response));
	}

	private class ServletHandlerThread implements Runnable {

		private HttpRequest request;
		private HttpResponse response;

		private ServletHandlerThread(HttpRequest request, HttpResponse response) {
			this.request = request;
			this.response = response;
		}

		@Override
		public void run() {
			PrintStream output = new PrintStream(response.getOutput());
			output.println("HTTP/1.1 200 OK");
			output.println("Server: Apache Tomcat");
			output.println("Date: " + new Date().toString());
			output.println("Content-Type: text/html; charset=UTF-8");
			output.println("");
			output.println("Tomcat Test");

			request.close();
			response.close();
		}

	}

}
