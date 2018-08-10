package org.txazo.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOMainReactor implements Runnable {

	private Selector selector;
	private Selector readSelector;
	private Selector writeSelector;
	private ServerSocketChannel server;
	private Acceptor acceptor;
	private SocketProcessor processor;

	public NIOMainReactor(int port) throws IOException {
		selector = Selector.open();
		server = ServerSocketChannel.open();
		server.configureBlocking(false);
		server.socket().bind(new InetSocketAddress(port));
		server.register(selector, SelectionKey.OP_ACCEPT);
		acceptor = new Acceptor();
		processor = new SocketProcessor();
	}

	public void bindReadReactor(NIOReactor reactor) throws ClosedChannelException {
		readSelector = reactor.getSelector();
		server.register(readSelector, SelectionKey.OP_ACCEPT);
		processor.setWriteSelector(readSelector);
	}

	public void bindWriteReactor(NIOReactor reactor) throws ClosedChannelException {
		writeSelector = reactor.getSelector();
		server.register(writeSelector, SelectionKey.OP_ACCEPT);
	}

	@Override
	public void run() {
		SelectionKey selectionKey = null;
		Set<SelectionKey> selectionKeys = null;
		try {
			while (true) {
				selector.select();
				selectionKeys = selector.selectedKeys();
				for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext();) {
					selectionKey = iterator.next();
					if (selectionKey.isAcceptable()) {
						acceptor.accept(selectionKey);
					}
				}
				selectionKeys.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class Acceptor {

		public void accept(SelectionKey selectionKey) {
			try {
				SocketChannel socketChannel = server.accept();
				if (socketChannel != null) {
					new SocketReadHandler(readSelector, socketChannel, processor);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
