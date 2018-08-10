package org.txazo.ws.rpc.hessian.client;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.caucho.hessian.server.HessianServlet;

/**
 * Java Hessian
 * 
 * @author txazo
 * 
 */
public class HessianServiceImpl extends HessianServlet implements HessianService {

	private static final long serialVersionUID = 6230989992411530072L;

	@Override
	public String getRemoteTime() {
		return "RemoteTime: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

}
