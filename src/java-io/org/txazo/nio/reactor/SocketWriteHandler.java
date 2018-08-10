package org.txazo.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketWriteHandler implements Runnable {

	private SocketChannel socketChannel;
	private String output;

	public SocketWriteHandler(Selector selector, SocketChannel socketChannel, String output) throws IOException {
		this.socketChannel = socketChannel;
		this.output = output;

		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_WRITE, this);
	}

	@Override
	public void run() {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.clear();
			buffer.put(output.getBytes());
			buffer.flip();

			socketChannel.write(buffer);
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
