package org.txazo.framework.httpclient.crawl;

import java.io.IOException;
import java.util.Map;

public interface HttpCrawl {

	public String get(String url, boolean login) throws IOException;

	public String post(String url, boolean login, Map<String, String> params) throws IOException;

}
