package org.txazo.framework.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Consts;
import org.apache.http.Header;
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
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * AutoLogin
 */
public class AutoLogin extends BaseTest {

	@Test
	public void testFormPost() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("_eventId", "submit"));
		formParams.add(new BasicNameValuePair("username", "txazo"));
		formParams.add(new BasicNameValuePair("password", "528463"));

		HttpEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			System.out.println("------------------------------------------------------------------");
			HttpGet httpGet = new HttpGet("https://passport.csdn.net/account/login");
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("Referer", "https://passport.csdn.net/account/login");
			httpGet.setHeader("Host", "passport.csdn.net");
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
			response = httpClient.execute(httpGet);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			entity = response.getEntity();

			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + " " + header.getValue());
			}

			String lt = null;
			String execution = null;
			if (entity != null) {
				String content = EntityUtils.toString(entity);
				logger.info(content);

				Parser parser = Parser.createParser(content, "utf-8");

				NodeList nodeList = null;
				AndFilter inputTag = new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("name", "lt"));
				try {
					nodeList = parser.parse(inputTag);
				} catch (ParserException e) {
				}
				Pattern pattern = Pattern.compile("value=\"([\\-\\w]+)\"");
				Matcher matcher = null;
				String html = null;
				int length = nodeList.size();
				for (int i = 0; i < length; i++) {
					html = nodeList.elementAt(i).toHtml();
					System.out.println("node------------------------------------------------------------------" + html);
					matcher = pattern.matcher(html);
					if (matcher.find()) {
						lt = matcher.group(1);
						System.out.println("value------------------------------------------------------------------" + matcher.group(1));
					}
				}

				parser.reset();

				nodeList = null;
				inputTag = new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("name", "execution"));
				try {
					nodeList = parser.parse(inputTag);
				} catch (ParserException e) {
				}
				pattern = Pattern.compile("value=\"([\\-\\w]+)\"");
				matcher = null;
				html = null;
				length = nodeList.size();
				for (int i = 0; i < length; i++) {
					html = nodeList.elementAt(i).toHtml();
					System.out.println("node------------------------------------------------------------------" + html);
					matcher = pattern.matcher(html);
					if (matcher.find()) {
						execution = matcher.group(1);
						System.out.println("value------------------------------------------------------------------" + matcher.group(1));
					}
				}
				System.out.println("------------------------------------------------------------------");
			}

			formParams.add(new BasicNameValuePair("execution", execution));
			formParams.add(new BasicNameValuePair("lt", lt));
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

			System.out.println("Login------------------------------------------------------------------Login");
			HttpPost httpPost = new HttpPost("https://passport.csdn.net/account/login");
			httpPost.setEntity(formEntity);
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Referer", "https://passport.csdn.net/account/login");
			httpPost.setHeader("Host", "passport.csdn.net");
			httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
			response = httpClient.execute(httpPost);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}

			System.out.println("------------------------------------------------------------------");
			httpGet = new HttpGet(
					"https://passport.csdn.net/home/ssoindex?userName=txazo&userInfo=Mu3yo95i3/EIut7dEiSb4ex4gD5BQL7IoKsINoYymfU+Dro4koqyy8+N/oQs3vtmK71DvJQPVbNq00ab1+am+jMwxo7DSzmZr0QDBib5BE0WO7E8mhDC3X6b7ojMT0on&=exp");
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("Referer", "http://my.csdn.net/my/mycsdn");
			httpGet.setHeader("Host", "my.csdn.net");
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
			response = httpClient.execute(httpGet);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}

			headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + " " + header.getValue());
			}

			System.out.println("------------------------------------------------------------------");
			httpGet = new HttpGet("http://my.csdn.net/");
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("Referer", "http://my.csdn.net/my/mycsdn");
			httpGet.setHeader("Host", "my.csdn.net");
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
			response = httpClient.execute(httpGet);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}
		} finally {
			response.close();
			httpClient.close();
		}
	}

	@Test
	public void testFormPost1() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("_eventId", "submit"));
		formParams.add(new BasicNameValuePair("username", "txazo"));
		formParams.add(new BasicNameValuePair("password", "528463"));
		formParams.add(new BasicNameValuePair("execution", "e7s1"));
		formParams.add(new BasicNameValuePair("lt", "LT-404869-VLzakArMOdbp7EJppEoyrTZQkWc6fF"));
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

		HttpEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet("https://passport.csdn.net/account/login");
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpGet.setHeader("Accept-Encoding", "gzip, deflate");
			httpGet.setHeader("Accept-Language	", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			httpGet.setHeader("Connection", "keep-alive");
			httpGet.setHeader("Host", "passport.csdn.net");
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
			httpGet.setHeader("Referer", "https://passport.csdn.net/account/login");
			response = httpClient.execute(httpGet);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}

			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + " " + header.getValue());
			}

			HttpPost httpPost = new HttpPost("https://passport.csdn.net/account/login");
			httpPost.setEntity(formEntity);
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpPost.setHeader("Accept-Encoding", "gzip, deflate");
			httpPost.setHeader("Accept-Language	", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			httpPost.setHeader("Connection", "keep-alive");
			httpPost.setHeader("Host", "passport.csdn.net");
			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:34.0) Gecko/20100101 Firefox/34.0");
			httpPost.setHeader("Referer", "https://passport.csdn.net/account/login");
			response = httpClient.execute(httpPost);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			entity = response.getEntity();
			if (entity != null) {
				logger.info(EntityUtils.toString(entity));
			}

			headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + " " + header.getValue());
			}
		} finally {
			response.close();
			httpClient.close();
		}
	}

}
