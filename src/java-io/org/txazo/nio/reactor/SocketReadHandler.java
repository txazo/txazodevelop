package org.txazo.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketReadHandler implements Runnable {

	private SocketChannel socketChannel;
	private SocketProcessor processor;

	public SocketReadHandler(Selector selector, SocketChannel socketChannel, SocketProcessor processor) throws IOException {
		this.socketChannel = socketChannel;
		this.processor = processor;

		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ, this);
	}

	@Override
	public void run() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.clear();
			int length = socketChannel.read(buffer);
			buffer.flip();
			String input = new String(buffer.array(), 0, length);

			processor.process(socketChannel, input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
