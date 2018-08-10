package org.txazo.java.network.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.net.URLEncoder java.net.URLDecoder
 * 
 * @author txazo
 * 
 */
public class URLEnDecoderTest extends BaseTest {

	@Test
	public void testURLEncoder() throws UnsupportedEncodingException {
		logger.info(URLEncoder.encode("http://www.baidu.com/", "UTF-8"));
	}

	/**
	 * Ajax中文乱码解决方案
	 * 
	 * <pre>
	 * 1) 浏览器端编码: encodeURIComponent(param);
	 * 2) 服务器端解码: URLDecoder.decode(param, "utf-8");
	 * </pre>
	 */
	@Test
	public void testURLDecoder() throws UnsupportedEncodingException {
		logger.info(URLDecoder.decode("http%3A%2F%2Fwww.baidu.com%2F", "UTF-8"));
	}

}
