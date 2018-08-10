package org.txazo.io.readerwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符缓冲流 - 过滤流
 * 
 * <pre>
 * java.io.BufferedReader
 * java.io.BufferedWriter
 * </pre>
 * 
 * @author txazo
 * 
 */
public class BufferedReaderWriterTest extends BaseTest {

	@Test
	public void testBufferedReaderWriter() {
		File file = new File("D:/test/in.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file)); BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("1234");
			bw.newLine();
			bw.write("5678");
			bw.flush();

			String line = null;
			while ((line = br.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
