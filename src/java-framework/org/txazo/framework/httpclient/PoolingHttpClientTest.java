package org.txazo.framework.httpclient;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * PoolingHttpClient
 * 
 * @author txazo
 * 
 */
public class PoolingHttpClientTest extends BaseTest {

	@Test
	public void testConnectionPool() throws InterruptedException {
		/** 连接池管理器 */
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		/** 最大连接数 */
		connectionManager.setMaxTotal(200);
		/** 默认单个路由最大连接数 */
		connectionManager.setDefaultMaxPerRoute(20);
		/** 目标主机的最大连接数 */
		HttpHost httpHost = new HttpHost("127.0.0.1", 80);
		connectionManager.setMaxPerRoute(new HttpRoute(httpHost), 30);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

		for (int i = 0; i < 1000; i++) {
			ThreadPool.getInstance().addThread(new GetThread("http://127.0.0.1/spring/login.html?user=root&password=root", httpClient));
		}

		Thread.sleep(60 * 1000);

		try {
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class GetThread implements Runnable {

		private String url;
		private final CloseableHttpClient httpClient;
		private final HttpContext context;

		public GetThread(String url, CloseableHttpClient httpClient) {
			this.url = url;
			this.httpClient = httpClient;
			this.context = HttpClientContext.create();
		}

		@Override
		public void run() {
			try {
				HttpGet httpGet = new HttpGet(url);
				CloseableHttpResponse response = httpClient.execute(httpGet, context);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						logger.info(EntityUtils.toString(entity));
					}
				} finally {
					response.close();
				}
			} catch (ClientProtocolException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	private static class ThreadPool {

		private static ThreadPool threadPool = null;

		private int poolSize = 100;
		private ExecutorService pool = null;

		private ThreadPool() {
			pool = Executors.newFixedThreadPool(poolSize);
		}

		public static ThreadPool getInstance() {
			if (threadPool == null) {
				synchronized (ThreadPool.class) {
					if (threadPool == null) {
						threadPool = new ThreadPool();
					}
				}
			}
			return threadPool;
		}

		public void addThread(Runnable thread) {
			pool.execute(thread);
		}

	}

}
