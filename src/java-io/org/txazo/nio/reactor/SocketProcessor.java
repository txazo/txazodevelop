package org.txazo.nio.reactor;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketProcessor {

	private Selector writeSelector;
	private ExecutorService threadPool;

	public SocketProcessor() {
		threadPool = Executors.newFixedThreadPool(100);
	}

	public void process(SocketChannel socketChannel, String input) {
		threadPool.execute(new SocketEvent(writeSelector, socketChannel, input));
	}

	public void setWriteSelector(Selector writeSelector) {
		this.writeSelector = writeSelector;
	}

}
