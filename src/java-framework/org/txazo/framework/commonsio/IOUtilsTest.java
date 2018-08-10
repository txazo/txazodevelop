package org.txazo.framework.commonsio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IOUtilsTest {

	@Test
	public void testToString() {
		InputStream in = null;
		try {
			in = new URL("http://www.baidu.com").openStream();
			/**
			 * 写入org.apache.commons.io.output.StringBuilderWriter流<br />
			 * StringBuilderWriter内部使用StringBuilder存储
			 */
			String data = IOUtils.toString(in);
			System.out.println(data);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);

			/** IOUtils.closeQuietly(in) */
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testCopy() {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new BufferedInputStream(new FileInputStream("D:/fis.txt"));
			fos = new BufferedOutputStream(new FileOutputStream("D:/fos.txt"));

			/** 读取IO流 */
			IOUtils.copy(fis, fos);

			/** IOUtils.copy(fis, fos) */
			int length = -1;
			byte[] buffer = new byte[4096];
			while ((length = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(fis);
		}
	}

}
