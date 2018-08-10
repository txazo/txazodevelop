package org.txazo.java.network.address;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.net.InetAddress
 * 
 * @author txazo
 * 
 */
public class InetAddressTest extends BaseTest {

	@Test
	public void testInetAddress() throws UnknownHostException {
		String host = "www.baidu.com";
		byte[] ip = new byte[] { (byte) 180, 97, 33, 67 };
		byte[] innerIp = new byte[] { 10, 10, 121, (byte) 196 };

		/** 本地IP */
		InetAddress address = InetAddress.getLocalHost();
		logger.info("{}\t{}", address.getHostName(), address.getHostAddress());

		/** 局域网IP(根据IP查主机名, 只限局域网) */
		address = InetAddress.getByAddress(innerIp);
		logger.info("{}\t{}", address.getHostName(), address.getHostAddress());

		/** IP */
		address = InetAddress.getByAddress(ip);
		logger.info(address.getHostAddress());

		/** 域名 + IP */
		address = InetAddress.getByAddress(host, ip);
		logger.info("{}\t{}", address.getHostName(), address.getHostAddress());

		/** 域名 */
		address = InetAddress.getByName(host);
		logger.info("{}\t{}", address.getHostName(), address.getHostAddress());

		/** 域名下所有IP */
		InetAddress[] addresses = InetAddress.getAllByName(host);
		for (InetAddress a : addresses) {
			logger.info("{}\t{}", a.getHostName(), a.getHostAddress());
		}
	}

}
