package org.txazo.pattern.structural.adapter.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class IOAdapterTest extends BaseTest {

	/**
	 * 字节流到字符流的适配器
	 */
	@Test
	public void testIOAdapter() throws IOException {
		File file = new File("D:/io.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		Writer writer = new OutputStreamWriter(new FileOutputStream(file));
		for (int i = 0; i < 10; i++) {
			IOUtils.write("IOAdapter " + i + System.getProperty("line.separator"), writer);
		}
		IOUtils.closeQuietly(writer);

		Reader reader = new InputStreamReader(new FileInputStream(file));
		List<String> lines = IOUtils.readLines(reader);
		IOUtils.closeQuietly(reader);

		for (String line : lines) {
			logger.info(line);
		}

		file.delete();
	}

}
