package org.txazo.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * HTTP协议 - 超文本传输协议
 * 
 * <pre>
 * 1) 无状态
 * 2) 建立在TCP连接上
 * </pre>
 */
public class HttpTest extends BaseTest {

	/**
	 * HTTP Request - 请求
	 * 
	 * <pre>
	 * <request line>
	 * <headers>
	 * <blank line>
	 * [<request-body>]
	 * </pre>
	 */

	/**
	 * HTTP Response - 响应
	 * 
	 * <pre>
	 * <status line>
	 * <headers>
	 * <blank line>
	 * [<response-body>]
	 * </pre>
	 */

	/**
	 * GET和POST的区别
	 * 
	 * <pre>
	 * 1) 参数: GET通过URL提交数据, 采用ASCII编码, POST将数据封装在请求体中
	 * 2) 大小: GET传输的数据不超过2k, POST传输的数据无长度限制
	 * 3) 安全: POST安全性比GET高, GET的提交的数据会附加在URL之后, 可被缓存和保留在浏览器历史记录中, POST不会
	 * </pre>
	 */

	/**
	 * HTTP响应状态码
	 * 
	 * <pre>
	 * 200: 请求成功
	 * 304: 请求资源未修改
	 * 403: 服务器拒绝执行请求
	 * 404: 请求无法找到资源
	 * 405: 请求方法被禁止
	 * 500: 服务器内部错误，无法完成请求
	 * 503: 服务期超载或维护，暂时无法处理请求
	 * </pre>
	 */

	/**
	 * HTTP GET
	 * 
	 * <pre>
	 * GET /spring/login.html?user=root&password=root HTTP/1.1
	 * Host: 127.0.0.1
	 * </pre>
	 */
	@Test
	public void testHttpGet() throws IOException {
		Socket socket = new Socket("127.0.0.1", 80);

		OutputStream out = socket.getOutputStream();
		out.write("GET /spring/login.html?user=root&password=root HTTP/1.1\r\n".getBytes());
		out.write("Host: 127.0.0.1\r\n".getBytes());
		out.write("\r\n".getBytes());
		out.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			logger.info(line);
		}

		br.close();
		out.close();
		socket.close();
	}

	/**
	 * HTTP POST
	 * 
	 * <pre>
	 * POST /spring/login.html HTTP/1.1
	 * Host: 127.0.0.1
	 * Content-Length: 23
	 * Content-Type: application/x-www-form-urlencoded
	 * 
	 * user=root&password=root
	 * </pre>
	 */
	@Test
	public void testHttpPost() throws IOException {
		Socket socket = new Socket("127.0.0.1", 80);

		String params = "user=root&password=root";
		OutputStream out = socket.getOutputStream();
		out.write("POST /spring/login.html HTTP/1.1\r\n".getBytes());
		out.write("Host: 127.0.0.1\r\n".getBytes());
		out.write(new String("Content-Length: " + params.length() + "\r\n").getBytes());
		out.write("Content-Type: application/x-www-form-urlencoded\r\n".getBytes());
		out.write("\r\n".getBytes());
		out.write(params.getBytes());
		out.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			logger.info(line);
		}

		br.close();
		out.close();
		socket.close();
	}

}
