package org.txazo.cache.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Cookie
 * 
 * <pre>
 * 1) name: Cookie名
 * 2) value: Cookie值
 * 3) domain: Cookie的域名
 * 4) path: Cookie的路径
 * 5) maxAge: Cookie的过期时间，正数表示过期时间，0表示删除Cookie，负数表示浏览器关闭后删除Cookie
 * </pre>
 * 
 * @author txazo
 * 
 */
public class CookieTest extends BaseTest {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Test
	public void testCookie() {
		/** 获取Cookie */
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			logger.info("{}:{}", cookie.getName(), cookie.getValue());
		}

		/** 添加Cookie */
		Cookie cookie = new Cookie("name", "txazo");
		cookie.setDomain(".txazo.com");
		cookie.setPath("/");
		cookie.setMaxAge(3600);
		response.addCookie(cookie);

		/** 删除Cookie */
		cookie = new Cookie("name", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}
