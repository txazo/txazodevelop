package org.txazo.io.readerwriter;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符数组流 - 节点流
 * 
 * <pre>
 *  java.io.CharArrayReader
 *  java.io.CharArrayWriter
 * </pre>
 * 
 * @author txazo
 * 
 */
public class CharArrayReaderWriterTest extends BaseTest {

	@Test
	public void testCharArrayReaderWriter() {
		char[] in = "12345678".toCharArray();
		try (CharArrayReader car = new CharArrayReader(in); CharArrayWriter caw = new CharArrayWriter()) {
			char[] temp = new char[4];
			car.read(temp);
			logger.info(new String(temp));
			car.read(temp, 0, 4);
			logger.info(new String(temp));

			caw.write("1234".toCharArray());
			caw.write(new char[] { '5', '6', '7', '8' }, 0, 4);
			logger.info(caw.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
