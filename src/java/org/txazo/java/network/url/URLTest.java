package org.txazo.java.network.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.net.URL
 * 
 * @author txazo
 * 
 */
public class URLTest extends BaseTest {

	@Test
	public void testURL() throws MalformedURLException {
		URL url = new URL("http://127.0.0.1/spring/login.html?user=root&password=root");

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}
	}

}
