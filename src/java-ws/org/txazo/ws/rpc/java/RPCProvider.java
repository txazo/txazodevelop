package org.txazo.ws.rpc.java;

public class RPCProvider {

	public static void main(String[] args) throws Exception {
		RPC rpc = new RPCFramework();
		rpc.register(new RPCServiceImpl(), 9999);

		Thread.sleep(10 * 1000);
		rpc.unRegister(RPCServiceImpl.class);

		Thread.sleep(60 * 1000);
	}

}
