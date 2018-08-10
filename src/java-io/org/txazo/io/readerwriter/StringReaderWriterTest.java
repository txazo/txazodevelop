package org.txazo.io.readerwriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符串流 - 节点流
 * 
 * <pre>
 * java.io.StringReader
 * java.io.StringWriter
 * </pre>
 * 
 * @author txazo
 * 
 */
public class StringReaderWriterTest extends BaseTest {

	@Test
	public void testStringReaderWriter() {
		String in = "12345678";
		try (StringReader sr = new StringReader(in); StringWriter sw = new StringWriter()) {
			char[] temp = new char[4];
			sr.read(temp);
			logger.info(new String(temp));
			sr.read(temp, 0, 4);
			logger.info(new String(temp));

			sw.write("1234");
			sw.write("5678", 0, 4);
			logger.info(sw.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
