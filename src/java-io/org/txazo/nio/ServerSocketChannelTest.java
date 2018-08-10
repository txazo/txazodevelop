package org.txazo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.txazo.test.base.BaseTest;

/**
 * java.nio.channels.ServerSocketChannel
 * 
 * @author txazo
 * 
 */
public class ServerSocketChannelTest extends BaseTest {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(9999));
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			int readyChannels = selector.select();
			if (readyChannels < 1) {
				continue;
			}
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			SelectionKey selectionKey = null;
			for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext();) {
				selectionKey = iterator.next();
				handleSelectionKey(selector, selectionKey);
				iterator.remove();
			}
		}
	}

	private static void handleSelectionKey(Selector selector, SelectionKey selectionKey) throws IOException {
		SocketChannel client = null;
		ServerSocketChannel server = null;

		if (selectionKey.isAcceptable()) {
			logger.info("Server isAcceptable");

			server = (ServerSocketChannel) selectionKey.channel();
			client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			logger.info("Server isReadable");

			client = (SocketChannel) selectionKey.channel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.clear();
			int length = client.read(buffer);
			if (length > 0) {
				logger.info(new String(buffer.array(), 0, length));
			}
			client.register(selector, SelectionKey.OP_WRITE);
		} else if (selectionKey.isWritable()) {
			logger.info("Server isWritable");

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.clear();
			buffer.put("server response".getBytes());
			buffer.flip();

			client = (SocketChannel) selectionKey.channel();
			client.write(buffer);

			client.close();
		}
	}

}
