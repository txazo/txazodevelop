package org.txazo.framework.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * HttpClient
 * 
 * @author txazo
 * 
 */
public class HttpClientTest extends BaseTest {

	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();

		HttpGet httpGet = new HttpGet("http://127.0.0.1/spring/login.html?user=root&password=root");
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}
		} finally {
			response.close();
			httpClient.close();
		}
	}

	@Test
	public void testFormPost() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("user", "root"));
		formParams.add(new BasicNameValuePair("password", "root"));
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

		HttpPost httpPost = new HttpPost("http://127.0.0.1/spring/login.html");
		httpPost.setEntity(formEntity);
		httpPost.setConfig(requestConfig);

		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}
		} finally {
			response.close();
			httpClient.close();
		}
	}

}
