package org.txazo.nio.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOReadReactor implements NIOReactor, Runnable {

	private Selector selector;
	private Acceptor acceptor;

	public NIOReadReactor() {
		try {
			selector = Selector.open();
			acceptor = new Acceptor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Selector getSelector() {
		return selector;
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
					if (selectionKey.isReadable() || selectionKey.isWritable()) {
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

		private ExecutorService threadPool;

		public Acceptor() {
			threadPool = Executors.newFixedThreadPool(100);
		}

		public void accept(SelectionKey selectionKey) {
			Runnable runnable = (Runnable) selectionKey.attachment();
			if (runnable != null) {
				threadPool.execute(runnable);
			}
		}

	}

}
