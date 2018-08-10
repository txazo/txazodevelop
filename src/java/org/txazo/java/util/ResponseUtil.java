package org.txazo.java.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * HTTP Response工具类
 */
public class ResponseUtil {

	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain", text);
	}

	public static void renderXml(HttpServletResponse response, String text) {
		render(response, "text/xml", text);
	}

	public static void renderHtml(HttpServletResponse response, String text) {
		render(response, "text/html", text);
	}

	public static void renderJavaScript(HttpServletResponse response, String text) {
		render(response, "text/javascript", text);
	}

	public static void renderJson(HttpServletResponse response, String text) {
		render(response, "application/json", text);
	}

	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType + "; charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
