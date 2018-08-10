package org.txazo.ws.rpc.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.ws.rpc.thrift.core.ThriftService;
import org.txazo.ws.rpc.thrift.core.impl.ThriftServiceImpl;

/**
 * 线程池服务模型 - 使用标准的阻塞式IO, 预先创建一组线程处理请求
 * 
 * @author txazo
 * 
 */
public class ThriftPoolTest extends BaseTest {

	private static final int port = 9999;

	public static void main(String[] args) throws TTransportException {
		TProcessor tProcessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		TServerTransport tServerTransport = new TServerSocket(port);
		TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(tServerTransport);
		tArgs.processor(tProcessor);
		tArgs.protocolFactory(new TBinaryProtocol.Factory());
		TServer server = new TThreadPoolServer(tArgs);
		server.serve();
	}

	@Test
	public void testThriftClient() throws Exception {
		TTransport transport = new TSocket("127.0.0.1", port, 30000);
		TProtocol protocol = new TBinaryProtocol(transport);
		ThriftService.Client client = new ThriftService.Client(protocol);
		transport.open();
		Assert.assertTrue(client.login(1001, "root"));
		Assert.assertTrue(client.login(1002, "admin"));
		Assert.assertFalse(client.login(1003, "admin"));
	}

}
