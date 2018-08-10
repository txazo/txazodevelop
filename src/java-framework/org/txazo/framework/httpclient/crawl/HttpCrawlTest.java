package org.txazo.framework.httpclient.crawl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class HttpCrawlTest extends BaseTest {

	@Test
	public void testHttpCrawlGet() throws IOException {
		HttpCrawl httpCrawl = new CsdnHttpCrawl();
		String html = httpCrawl.get("http://my.csdn.net/", true);
		System.out.println(html);
	}

	@Test
	public void testHttpCrawlPost() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("tag2", "java");
		params.put("tags", "Java");
		params.put("titl", "tetete434555");
		params.put("cont", "sdsssssssssdsssssssssdsssssssssdssssssss");

		CsdnHttpCrawl httpCrawl = new CsdnHttpCrawl();
		httpCrawl.edit(params);
	}

	@Test
	public void delete() throws IOException {
		CsdnHttpCrawl httpCrawl = new CsdnHttpCrawl();
		httpCrawl.delete(42878559);
	}

}
