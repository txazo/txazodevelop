package org.txazo.ws.rpc.hessian.spring;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class HessianServiceImpl implements HessianService {

	@Override
	public String getRemoteTime() {
		return "RemoteTime: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

}
