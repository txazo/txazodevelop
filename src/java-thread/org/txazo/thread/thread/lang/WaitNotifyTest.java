package org.txazo.thread.thread.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.txazo.test.base.BaseTest;

/**
 * wait notify notifyAll
 * 
 * <pre>
 * wait
 * notify
 * notifyAll
 * </pre>
 * 
 * @author txazo
 * 
 */
public class WaitNotifyTest extends BaseTest {

	@Test
	public void testThreadWaitNotify() throws InterruptedException {
		Server server = new Server();
		Thread thread = new Thread(server);
		thread.start();

		for (int i = 0; i < 10; i++) {
			Thread.sleep(500);

			server.request();
		}

		server.shutdown();
	}

	private class Server implements Runnable {

		private int count = 1;
		private boolean stopped = false;
		private boolean available = false;
		private Logger logger = LoggerFactory.getLogger("stdout");

		@Override
		public void run() {
			while (!stopped) {
				process();
			}
		}

		public synchronized void process() {
			while (!available) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			logger.info("process {}", count++);

			available = false;
			notifyAll();
		}

		public synchronized void request() {
			while (available) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			logger.info("request {}", count);

			available = true;
			notifyAll();
		}

		public void shutdown() {
			stopped = true;
		}

	}

}
