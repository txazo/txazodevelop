package org.txazo.io.readerwriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符文件流 - 节点流
 * 
 * <pre>
 * java.io.FileReader
 * java.io.FileWriter
 * </pre>
 * 
 * @author txazo
 * 
 */
public class FileReaderWriterTest extends BaseTest {

	@Test
	public void testFileReaderWriter() {
		File file = new File("D:/test/in.txt");
		try (FileReader fr = new FileReader(file); FileWriter fw = new FileWriter(file)) {
			fw.write("1234");
			fw.write("5678");
			fw.flush();

			int length = -1;
			char[] temp = new char[1024];
			while ((length = fr.read(temp)) != -1) {
				logger.info(new String(temp, 0, length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
