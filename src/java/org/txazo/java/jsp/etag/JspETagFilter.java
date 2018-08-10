package org.txazo.java.jsp.etag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * JspETagFilter
 * 
 * @author txazo
 * @since 2014-03-03
 * 
 */
public class JspETagFilter implements Filter {

	public static final String ETAG = "ETag";
	public static final String ETAG_PREFIX = "ETag/";
	public static final String ETAG_SUFFIX = "/";
	public static final String IF_NONE_MATCH = "If-None-Match";
	public static final String LAST_MODIFIED = "Last-Modified";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getServletPath();

		if (uri.endsWith(".jsp")) {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ETagResponseWrapper wrappedResponse = new ETagResponseWrapper(response, buffer);
			chain.doFilter(req, wrappedResponse);
			wrappedResponse.flushBuffer();

			byte[] bytes = buffer.toByteArray();

			String etag = ETAG_PREFIX + DigestUtils.md5Hex(bytes) + ETAG_SUFFIX;
			String preETag = request.getHeader(IF_NONE_MATCH);

			response.setHeader(ETAG, etag);
			if (preETag != null && preETag.equals(etag)) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			} else {
				response.setDateHeader(LAST_MODIFIED, System.currentTimeMillis());

				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(bytes);
				outputStream.flush();
				outputStream.close();
			}
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
	}

}
