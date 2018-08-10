package org.txazo.ws.rpc.thrift;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.ws.rpc.thrift.core.ThriftService;
import org.txazo.ws.rpc.thrift.core.ThriftService.AsyncClient.login_call;
import org.txazo.ws.rpc.thrift.core.impl.ThriftServiceImpl;

/**
 * 异步的服务端模型
 * 
 * @author txazo
 * 
 */
public class ThriftAsyncTest extends BaseTest {

	private static final int port = 9999;

	public static void main(String[] args) throws TTransportException {
		TProcessor tProcessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		TNonblockingServerSocket tServerTransport = new TNonblockingServerSocket(port);
		TNonblockingServer.Args tArgs = new TNonblockingServer.Args(tServerTransport);
		tArgs.processor(tProcessor);
		tArgs.transportFactory(new TFramedTransport.Factory());
		tArgs.protocolFactory(new TCompactProtocol.Factory());
		TServer server = new TNonblockingServer(tArgs);
		server.serve();
	}

	@Test
	public void testThriftClient() throws Exception {
		TAsyncClientManager clientManager = new TAsyncClientManager();
		TNonblockingTransport transport = new TNonblockingSocket("127.0.0.1", port, 30000);
		TProtocolFactory tprotocol = new TCompactProtocol.Factory();
		ThriftService.AsyncClient asyncClient = new ThriftService.AsyncClient(tprotocol, clientManager, transport);
		CountDownLatch latch = new CountDownLatch(1);
		AsynCallback callBack = new AsynCallback(latch);
		asyncClient.login(1001, "root", callBack);
		latch.await(30, TimeUnit.SECONDS);
	}

	public class AsynCallback implements AsyncMethodCallback<login_call> {

		private CountDownLatch latch;

		public AsynCallback(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void onComplete(login_call response) {
			try {
				boolean result = response.getResult();
				logger.info("{}", result);
			} catch (TException e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

		@Override
		public void onError(Exception arg0) {
			latch.countDown();
		}

	}

}
