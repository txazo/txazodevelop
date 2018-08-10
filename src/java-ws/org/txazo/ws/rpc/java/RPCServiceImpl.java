package org.txazo.ws.rpc.java;

public class RPCServiceImpl implements RPCService {

	@Override
	public String call(String message) {
		return "RPC: " + message;
	}

}
