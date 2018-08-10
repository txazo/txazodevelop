package org.txazo.java.web.filter.etag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ETag过滤器
 */
public class ETagFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ETagResponseWrapper wrappedResponse = new ETagResponseWrapper(response, buffer);
		chain.doFilter(req, wrappedResponse);
		wrappedResponse.flushBuffer();

		byte[] bytes = buffer.toByteArray();

		String etag = "W/\"" + DigestUtils.md5Hex(bytes) + "\"";

		String preETag = request.getHeader("If-None-Match");

		response.setHeader("ETag", etag);
		if (preETag != null && preETag.equals(etag)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		} else {
			response.setDateHeader("Last-Modified", System.currentTimeMillis());

			ServletOutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
			out.close();
		}
	}

	@Override
	public void destroy() {
	}

}
