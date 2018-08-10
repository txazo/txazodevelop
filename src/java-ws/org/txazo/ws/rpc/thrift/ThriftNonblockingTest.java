package org.txazo.ws.rpc.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.ws.rpc.thrift.core.ThriftService;
import org.txazo.ws.rpc.thrift.core.impl.ThriftServiceImpl;

/**
 * 非阻塞式IO服务模型 - 服务端和客户端需要指定 TFramedTransport数据传输的方式
 * 
 * @author txazo
 * 
 */
public class ThriftNonblockingTest extends BaseTest {

	private static final int port = 9999;

	public static void main(String[] args) throws TTransportException {
		TProcessor tProcessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		TNonblockingServerTransport tServerTransport = new TNonblockingServerSocket(port);
		TNonblockingServer.Args tArgs = new TNonblockingServer.Args(tServerTransport);
		tArgs.processor(tProcessor);
		tArgs.transportFactory(new TFramedTransport.Factory());
		tArgs.protocolFactory(new TCompactProtocol.Factory());
		TServer server = new TNonblockingServer(tArgs);
		server.serve();
	}

	@Test
	public void testThriftClient() throws Exception {
		TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", port, 30000));
		TProtocol protocol = new TCompactProtocol(transport);
		ThriftService.Client client = new ThriftService.Client(protocol);
		transport.open();
		Assert.assertTrue(client.login(1001, "root"));
		Assert.assertTrue(client.login(1002, "admin"));
		Assert.assertFalse(client.login(1003, "admin"));
	}

}
