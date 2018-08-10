package org.txazo.java.web.cookie;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * CookieHelper
 * 
 * @author txazo
 * 
 */
public class CookieHelper {

	private static int defaultMaxAge = 2592000;
	private static String cookieSeparator = "^~^";
	private static String equalSign = "=";

	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
		setCookie(response, cookieName, cookieValue, null, null, defaultMaxAge);
	}

	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge) {
		setCookie(response, cookieName, cookieValue, null, null, maxAge);
	}

	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path) {
		setCookie(response, cookieName, cookieValue, domain, path, defaultMaxAge);
	}

	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path, int maxAge) {
		if (response == null || StringUtils.isEmpty(cookieName)) {
			return;
		}

		Cookie cookie = new Cookie(cookieName, cookieValue);
		if (StringUtils.isNotEmpty(domain)) {
			cookie.setDomain(domain);
		}
		if (StringUtils.isNotEmpty(path)) {
			cookie.setPath(path);
		} else {
			cookie.setPath("/");
		}
		cookie.setMaxAge(maxAge >= 0 ? maxAge : -1);

		response.addCookie(cookie);
	}

	public static String getCookie(HttpServletRequest request, String cookieName) {
		if (request == null || StringUtils.isEmpty(cookieName)) {
			return null;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		if (request == null || response == null || StringUtils.isEmpty(cookieName)) {
			return;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1) {
			return;
		}

		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				cookie.setPath("/");
				cookie.setMaxAge(0);
				cookie.setValue(null);

				response.addCookie(cookie);
				break;
			}
		}
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, Map<String, String> valueMap) {
		setCookie(request, response, cookieName, valueMap, null, null, defaultMaxAge, true);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, Map<String, String> valueMap, int maxAge) {
		setCookie(request, response, cookieName, valueMap, null, null, maxAge, true);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, Map<String, String> valueMap, boolean isKeepBefore) {
		setCookie(request, response, cookieName, valueMap, null, null, defaultMaxAge, isKeepBefore);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, Map<String, String> valueMap, String domain, String path) {
		setCookie(request, response, cookieName, valueMap, domain, path, defaultMaxAge, true);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, Map<String, String> valueMap, String domain, String path, int maxAge,
			boolean isKeepBefore) {
		if (request == null || response == null || StringUtils.isEmpty(cookieName) || valueMap == null || valueMap.size() < 1) {
			return;
		}

		String cookieValue = null;
		if (isKeepBefore) {
			cookieValue = getCookie(request, cookieName);
			if (StringUtils.isEmpty(cookieValue)) {
				cookieValue = createCookieValue(valueMap);
			} else {
				String key = null;
				String value = null;
				Entry<String, String> entry = null;
				for (Iterator<Entry<String, String>> iterator = valueMap.entrySet().iterator(); iterator.hasNext();) {
					entry = iterator.next();
					key = entry.getKey();
					value = entry.getValue();

					if (StringUtils.isEmpty(key)) {
						continue;
					}

					if (value == null) {
						value = "";
					}

					int index = cookieValue.indexOf(cookieSeparator + key + equalSign);
					if (index != -1) {
						int next = cookieValue.indexOf(cookieSeparator, index + 1);
						cookieValue = cookieValue.substring(0, index) + cookieSeparator + key + equalSign + value + cookieValue.substring(next, cookieValue.length());
					} else {
						cookieValue += key + equalSign + value + cookieSeparator;
					}
				}
			}
		} else {
			cookieValue = createCookieValue(valueMap);
		}

		if (StringUtils.isEmpty(cookieValue)) {
			return;
		}

		setCookie(response, cookieName, cookieValue, domain, path, maxAge);
	}

	public static String getCookie(HttpServletRequest request, String cookieName, String paramName) {
		if (StringUtils.isEmpty(paramName)) {
			return null;
		}

		String cookieValue = getCookie(request, cookieName);
		if (StringUtils.isEmpty(cookieValue)) {
			return null;
		}

		int index = cookieValue.indexOf(cookieSeparator + paramName + equalSign);
		if (index != -1) {
			int start = cookieValue.indexOf(equalSign, index + 1);
			int end = cookieValue.indexOf(cookieSeparator, index + 1);
			return cookieValue.substring(start + 1, end);
		}

		return null;
	}

	private static String createCookieValue(Map<String, String> valueMap) {
		StringBuilder sb = new StringBuilder();

		String key = null;
		String value = null;
		Entry<String, String> entry = null;
		for (Iterator<Entry<String, String>> iterator = valueMap.entrySet().iterator(); iterator.hasNext();) {
			entry = iterator.next();
			key = entry.getKey();
			value = entry.getValue();
			if (StringUtils.isEmpty(key)) {
				continue;
			}

			if (value == null) {
				value = "";
			}

			sb.append(cookieSeparator);
			sb.append(key);
			sb.append(equalSign);
			sb.append(value);
		}

		if (StringUtils.isNotEmpty(sb.toString())) {
			sb.append(cookieSeparator);
		}

		return sb.toString();
	}

}
