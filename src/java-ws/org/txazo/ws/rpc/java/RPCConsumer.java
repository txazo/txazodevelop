package org.txazo.ws.rpc.java;

import org.txazo.test.base.BaseTest;

public class RPCConsumer extends BaseTest {

	public static void main(String[] args) throws Exception {
		RPC rpc = new RPCFramework();
		RPCService rpcService = rpc.getProxy(RPCService.class, "127.0.0.1", 9999);
		for (int i = 0; i < 10; i++) {
			logger.info("{}", rpcService.call("txazo"));
		}
	}

}
