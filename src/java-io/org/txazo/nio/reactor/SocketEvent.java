package org.txazo.nio.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketEvent implements Runnable {

	private Selector selector;
	private SocketChannel socketChannel;
	private String input;

	public SocketEvent(Selector selector, SocketChannel socketChannel, String input) {
		this.selector = selector;
		this.socketChannel = socketChannel;
		this.input = input;
	}

	@Override
	public void run() {
		try {
			System.out.println("----input: " + input);
			System.out.println("----ouput: " + input);
			new SocketWriteHandler(selector, socketChannel, input);
			System.out.println("----4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
