package org.txazo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.txazo.test.base.BaseTest;

/**
 * java.nio.channels.SocketChannel
 * 
 * @author txazo
 * 
 */
public class SocketChannelTest extends BaseTest {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();

		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

		SocketChannel client = null;
		ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
		ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

		while (true) {
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			SelectionKey selectionKey = null;
			for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext();) {
				selectionKey = iterator.next();

				if (selectionKey.isConnectable()) {
					logger.info("Client isConnectable");

					client = (SocketChannel) selectionKey.channel();
					if (client.isConnectionPending()) {
						client.finishConnect();
						sendBuffer.clear();
						sendBuffer.put("client request".getBytes());
						sendBuffer.flip();
						client.write(sendBuffer);
						client.register(selector, SelectionKey.OP_READ);
					}
				} else if (selectionKey.isReadable()) {
					logger.info("Client isReadable");

					client = (SocketChannel) selectionKey.channel();
					receiveBuffer.clear();
					int length = client.read(receiveBuffer);
					if (length > 0) {
						System.out.println(new String(receiveBuffer.array(), 0, length));
					}
					client.close();
				}

				iterator.remove();
			}
		}
	}

}
