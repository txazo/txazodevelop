package org.txazo.nio.reactor;

import java.io.IOException;

public class ServerBootstrap {

	private NIOMainReactor mainReactor;
	private NIOReadReactor readReactor;
	private NIOWriteReactor writeReactor;

	public ServerBootstrap(int port) throws IOException {
		mainReactor = new NIOMainReactor(port);
		readReactor = new NIOReadReactor();
		writeReactor = new NIOWriteReactor();

		mainReactor.bindReadReactor(readReactor);
		mainReactor.bindWriteReactor(writeReactor);

		new Thread(mainReactor).start();
		new Thread(readReactor).start();
		new Thread(writeReactor).start();
	}

	public static void main(String[] args) {
		try {
			new ServerBootstrap(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
