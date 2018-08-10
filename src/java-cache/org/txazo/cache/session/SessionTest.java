package org.txazo.cache.session;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Session
 * 
 * <pre>
 * Session失效:
 * 1) 关闭浏览器
 * 2) Session过期
 * 3) 调用HttpSession.invalidate()方法
 * </pre>
 * 
 * @author txazo
 * 
 */
public class SessionTest extends BaseTest {

	private HttpServletRequest request;

	@Test
	public void testSession() {
		String key = null;
		String value = null;
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
		value = (String) session.getAttribute(key);
		session.removeAttribute(key);
		session.invalidate();
		for (Enumeration<String> enumeration = session.getAttributeNames(); enumeration.hasMoreElements();) {
			key = enumeration.nextElement();
			value = (String) session.getAttribute(key);
		}
	}

}
