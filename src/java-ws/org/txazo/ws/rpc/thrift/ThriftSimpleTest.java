package org.txazo.ws.rpc.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
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
 * 单线程服务模型 - 一般用于测试
 * 
 * @author txazo
 * 
 */
public class ThriftSimpleTest extends BaseTest {

	private static final int port = 9999;

	public static void main(String[] args) throws TTransportException {
		TProcessor tProcessor = new ThriftService.Processor<ThriftService.Iface>(new ThriftServiceImpl());
		TServerTransport tServerTransport = new TServerSocket(port);
		TServer.Args tArgs = new TSimpleServer.Args(tServerTransport);
		tArgs.processor(tProcessor);
		tArgs.protocolFactory(new TBinaryProtocol.Factory());
		TServer server = new TSimpleServer(tArgs);
		server.serve();
	}

	@Test
	public void testThriftClient() throws Exception {
		TTransport tTransport = new TSocket("127.0.0.1", port, 30000);
		TProtocol tProtocol = new TBinaryProtocol(tTransport);
		ThriftService.Client client = new ThriftService.Client(tProtocol);
		tTransport.open();
		Assert.assertTrue(client.login(1001, "root"));
		Assert.assertTrue(client.login(1002, "admin"));
		Assert.assertFalse(client.login(1003, "admin"));
	}

}
