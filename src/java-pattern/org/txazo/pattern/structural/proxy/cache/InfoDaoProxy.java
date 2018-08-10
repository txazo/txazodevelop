package org.txazo.pattern.structural.proxy.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理模式 - 缓存代理
 * 
 * @author txazo
 * 
 */
public class InfoDaoProxy implements InfoDao {

	private InfoDao infoDao;

	private static Map<Long, String> cacheMap = new HashMap<Long, String>();

	public InfoDaoProxy() {
		infoDao = new InfoDaoImpl();
	}

	@Override
	public String getInfoById(Long id) {
		String info = null;
		if (cacheMap.containsKey(id)) {
			System.out.println("Get info by id " + id + " from cache");
			info = cacheMap.get(id);
		} else {
			info = infoDao.getInfoById(id);
			cacheMap.put(id, info);
		}
		return info;
	}

}
