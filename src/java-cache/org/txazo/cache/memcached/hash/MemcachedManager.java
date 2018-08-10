package org.txazo.cache.memcached.hash;

public interface MemcachedManager {

	public Object get(String key);

	public Object asyncGet(String key);

	public void set(String key, Object value, int expries);

	public String addServer(String ip, int port);

	public String removeServer(String ip, int port);

}
