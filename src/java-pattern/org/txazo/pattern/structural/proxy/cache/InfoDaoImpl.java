package org.txazo.pattern.structural.proxy.cache;

public class InfoDaoImpl implements InfoDao {

	@Override
	public String getInfoById(Long id) {
		System.out.println("Get info by id " + id);
		return "10000" + id;
	}

}
