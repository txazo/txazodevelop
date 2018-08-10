package org.txazo.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SelectorClient {

	private Selector selector;

	public SelectorClient() {
		try {
			selector = Selector.open();

			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_CONNECT, new ClientHandler("init", null));
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
		} catch (IOException e) {
			e.printStackTrace();
		}

		new Thread(new SelectorClientThread()).start();
	}

	public void register(SelectableChannel channel, int ops, Object att) {
		try {
			channel.register(selector, ops, att);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}

	public class SelectorClientThread implements Runnable {

		@Override
		public void run() {
			Handler handler = null;
			SocketChannel client = null;
			SelectionKey selectionKey = null;
			Set<SelectionKey> selectionKeys = null;
			ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
			ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

			try {
				while (true) {
					int readyChannels = selector.select();
					if (readyChannels < 1) {
						continue;
					}

					selectionKeys = selector.selectedKeys();
					for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext();) {
						selectionKey = iterator.next();

						if (selectionKey.isConnectable()) {
							client = (SocketChannel) selectionKey.channel();
							if (client.isConnectionPending()) {
								client.finishConnect();

								handler = (Handler) selectionKey.attachment();

								sendBuffer.clear();
								sendBuffer.put(handler.getRequest().getBytes());
								sendBuffer.flip();
								client.write(sendBuffer);

								client.register(selector, SelectionKey.OP_READ, handler);
							}
						} else if (selectionKey.isReadable()) {
							client = (SocketChannel) selectionKey.channel();

							receiveBuffer.clear();
							int length = client.read(receiveBuffer);
							if (length > 0) {
								handler = (ClientHandler) selectionKey.attachment();
								handler.response(new String(receiveBuffer.array(), 0, length));
							}
							client.close();
						}

						iterator.remove();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws Exception {
		SelectorClient client = new SelectorClient();

		for (int i = 0; i < 10; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("memberId", "6450244" + i);
			Handler handler = new ClientHandler("register", params);

			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			client.register(socketChannel, SelectionKey.OP_CONNECT, handler);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
		}

		Thread.sleep(10000);
	}

}
