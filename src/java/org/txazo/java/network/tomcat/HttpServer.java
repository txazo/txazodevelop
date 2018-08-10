package org.txazo.java.network.tomcat;

public class HttpServer {

	private HttpConnector connector;
	private ServletContainer container;

	public HttpServer() {
		connector = new HttpConnector();
		container = new ServletContainer();

		connector.setContainer(container);
	}

	public void start() {
		connector.start();
		new Thread(connector).start();
	}

}
