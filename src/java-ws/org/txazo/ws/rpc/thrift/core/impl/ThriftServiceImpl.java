package org.txazo.ws.rpc.thrift.core.impl;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.thrift.TException;
import org.txazo.ws.rpc.thrift.core.ThriftService;

public class ThriftServiceImpl implements ThriftService.Iface {

	private static Map<Integer, String> userMap = new HashedMap<Integer, String>();

	static {
		userMap.put(1001, "root");
		userMap.put(1002, "admin");
	}

	@Override
	public boolean login(int id, String password) throws TException {
		String p = userMap.get(id);
		return p == null ? false : p.equals(password);
	}

}
