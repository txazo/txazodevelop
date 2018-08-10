package org.txazo.framework.httpclient.crawl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlManager {

	private static HttpClient defaultHttpClient = HttpClients.createDefault();
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();

	public static HttpClient getHttpClient() {
		return defaultHttpClient;
	}

	public static HttpGet newHttpGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Accept-Language	", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
		httpGet.setHeader("Referer", url);
		return httpGet;
	}

	public static HttpPost newHttpHttpPost(String url) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language	", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
		httpPost.setHeader("Referer", url);
		return httpPost;
	}

	public static String getHtml(HttpClient httpClient, HttpGet httpGet) throws IOException {
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			return EntityUtils.toString(entity);
		}
		return null;
	}

	public static String getHtml(HttpClient httpClient, HttpPost httpPost) throws IOException {
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			return EntityUtils.toString(entity);
		}
		return null;
	}

}
