package org.txazo.framework.httpclient.crawl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * CSDN HttpCrawl
 */
public class CsdnHttpCrawl extends AbstractHttpCrawl {

	@Override
	public void login() throws IOException {
		String html = get("https://passport.csdn.net/account/login", false);

		String lt = null;
		String execution = null;
		if (StringUtils.isNoneBlank(html)) {
			String nodeHtml = null;
			NodeList nodeList = null;
			Matcher matcher = null;
			Pattern pattern = Pattern.compile("value=\"([\\-\\w]+)\"");
			Parser parser = Parser.createParser(html, "utf-8");

			AndFilter inputTag = new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("name", "lt"));
			try {
				nodeList = parser.parse(inputTag);
			} catch (ParserException e) {
			}
			nodeHtml = nodeList.elementAt(0).toHtml();
			matcher = pattern.matcher(nodeHtml);
			if (matcher.find()) {
				lt = matcher.group(1);
			}

			parser.reset();

			inputTag = new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("name", "execution"));
			try {
				nodeList = parser.parse(inputTag);
			} catch (ParserException e) {
			}
			nodeHtml = nodeList.elementAt(0).toHtml();
			matcher = pattern.matcher(nodeHtml);
			if (matcher.find()) {
				execution = matcher.group(1);
			}
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("_eventId", "submit");
		params.put("username", "txazo");
		params.put("password", "528463");
		params.put("lt", lt);
		params.put("execution", execution);

		post("https://passport.csdn.net/account/login", false, params);
	}

	public void edit(Map<String, String> params) throws IOException {
		String html = get("http://write.blog.csdn.net/postedit", true);

		String userinfo = null;
		if (StringUtils.isNoneBlank(html)) {
			String nodeHtml = null;
			NodeList nodeList = null;
			Matcher matcher = null;
			Pattern pattern = Pattern.compile("value=\"([\\-:\\w\\s]+)\"");
			Parser parser = Parser.createParser(html, "utf-8");

			AndFilter inputTag = new AndFilter(new TagNameFilter("input"), new HasAttributeFilter("id", "userinfo1"));
			try {
				nodeList = parser.parse(inputTag);
			} catch (ParserException e) {
			}
			nodeHtml = nodeList.elementAt(0).toHtml();
			matcher = pattern.matcher(nodeHtml);
			if (matcher.find()) {
				userinfo = matcher.group(1);
			}
		}

		String[] d = userinfo.split(" ");
		String[] dd = d[0].split("-");
		String[] ddd = d[1].split(":");
		int result = NumberUtils.toInt(dd[0]) + NumberUtils.toInt(dd[1]) + NumberUtils.toInt(dd[2]) + NumberUtils.toInt(ddd[0]) + NumberUtils.toInt(ddd[1])
				+ NumberUtils.toInt(ddd[2]);
		result = result % 60;
		String un = "txazo";
		int ascresult = 0;
		for (int i = 0; i < un.length(); i++) {
			ascresult += un.substring(i, i + 1).charAt(0);
		}
		ascresult = (ascresult % 60) * result;

		params.put("artid", "0");
		params.put("checkcode", "undefined");
		params.put("chnl", "16");
		params.put("comm", "2");
		params.put("desc", "");
		params.put("flnm", "");
		params.put("level", "0");
		params.put("stat", "publish");
		params.put("typ", "1");
		params.put("userinfo1", String.valueOf(ascresult));

		html = post("http://write.blog.csdn.net/postedit?edit=1&isPub=1&joinblogcontest=", false, params);
		System.out.println(html);
	}

	public void delete(long articleId) throws IOException {
		get("http://blog.csdn.net/txazo/article/delete?articleId=" + articleId, true);

		get("http://write.blog.csdn.net/postlist/0/all/deleted?t=deepdel&id=" + articleId, false);
	}

}
