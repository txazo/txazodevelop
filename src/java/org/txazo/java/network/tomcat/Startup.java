package org.txazo.java.network.tomcat;

public class Startup {

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.start();
	}

}
