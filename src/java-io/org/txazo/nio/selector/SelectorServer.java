package org.txazo.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServer {

	private Selector selector;
	private ServerProcesser processer;

	public SelectorServer() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init() throws IOException {
		selector = Selector.open();
		processer = new ServerProcesser();

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(8888));
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		new Thread(new SelectorServerThread()).start();
	}

	public class SelectorServerThread implements Runnable {

		@Override
		public void run() {
			SocketChannel client = null;
			ServerResponse response = null;
			ServerSocketChannel server = null;
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

						if (selectionKey.isAcceptable()) {
							server = (ServerSocketChannel) selectionKey.channel();
							client = server.accept();
							client.configureBlocking(false);
							client.register(selector, SelectionKey.OP_READ);
						} else if (selectionKey.isReadable()) {
							client = (SocketChannel) selectionKey.channel();

							receiveBuffer.clear();
							int length = client.read(receiveBuffer);
							if (length > 0) {
								response = new ServerResponse(new String(receiveBuffer.array(), 0, length));
								processer.process(response);
								client.register(selector, SelectionKey.OP_WRITE, response);
							}
						} else if (selectionKey.isWritable()) {
							client = (SocketChannel) selectionKey.channel();
							response = (ServerResponse) selectionKey.attachment();
							if (response != null && response.isReady()) {
								sendBuffer.clear();
								sendBuffer.put(response.getResponse().getBytes());
								sendBuffer.flip();

								client.write(sendBuffer);

								client.close();
							}
						}

						iterator.remove();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new SelectorServer();
	}

}
