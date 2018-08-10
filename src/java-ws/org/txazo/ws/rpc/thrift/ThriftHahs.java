package org.txazo.ws.rpc.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.ws.rpc.thrift.core.ThriftService;
import org.txazo.ws.rpc.thrift.core.impl.ThriftServiceImpl;

/**
 * 半同步半异步的服务端模型 - 服务端和客户端需要指定 TFramedTransport数据传输的方式
 * 
 * @author txazo
 * 
 */
public class ThriftHahs extends BaseTest {

	private static final int port = 9999;

	public static void main(String[] args) throws TTransportException {
		TProcessor tProcessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		TNonblockingServerSocket tServerTransport = new TNonblockingServerSocket(port);
		THsHaServer.Args tArgs = new THsHaServer.Args(tServerTransport);
		tArgs.processor(tProcessor);
		tArgs.transportFactory(new TFramedTransport.Factory());
		tArgs.protocolFactory(new TBinaryProtocol.Factory());
		TServer server = new THsHaServer(tArgs);
		server.serve();
	}

	@Test
	public void testThriftClient() throws Exception {
		TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", port, 30000));
		TProtocol protocol = new TBinaryProtocol(transport);
		ThriftService.Client client = new ThriftService.Client(protocol);
		transport.open();
		Assert.assertTrue(client.login(1001, "root"));
		Assert.assertTrue(client.login(1002, "admin"));
		Assert.assertFalse(client.login(1003, "admin"));
	}

}
