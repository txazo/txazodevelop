package org.txazo.framework.httpclient.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public abstract class AbstractHttpCrawl implements HttpCrawl {

	public abstract void login() throws IOException;

	@Override
	public String get(String url, boolean login) throws IOException {
		if (login) {
			login();
		}

		return CrawlManager.getHtml(CrawlManager.getHttpClient(), CrawlManager.newHttpGet(url));
	}

	@Override
	public String post(String url, boolean login, Map<String, String> params) throws IOException {
		if (login) {
			login();
		}

		HttpPost httpPost = CrawlManager.newHttpHttpPost(url);
		if (params != null && params.size() > 0) {
			String key = null;
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
				key = iterator.next();
				formParams.add(new BasicNameValuePair(key, params.get(key)));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			httpPost.setEntity(formEntity);
		}

		return CrawlManager.getHtml(CrawlManager.getHttpClient(), httpPost);
	}

}
