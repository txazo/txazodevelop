package org.txazo.ws.rpc.java;

public interface RPC {

	public void register(final Object service, final int port) throws Exception;

	public void unRegister(final Class<?> implClass);

	public <T> T getProxy(final Class<T> interfaceClass, final String host, final int port) throws Exception;

}
