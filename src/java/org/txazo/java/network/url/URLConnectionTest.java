package org.txazo.java.network.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.net.URLConnection
 * 
 * @author txazo
 * 
 */
public class URLConnectionTest extends BaseTest {

	@Test
	public void testURLConnection() throws IOException {
		URL url = new URL("http://127.0.0.1/spring/login.html");

		String params = "user=root&password=root";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Host", "127.0.0.1");
		connection.setRequestProperty("Content-Length", String.valueOf(params.length()));
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		connection.connect();

		OutputStream os = connection.getOutputStream();
		os.write(params.getBytes());
		os.flush();
		os.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			logger.info(line);
		}
		br.close();

		connection.disconnect();
	}

}
