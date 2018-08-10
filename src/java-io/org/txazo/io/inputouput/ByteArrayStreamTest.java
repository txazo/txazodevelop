package org.txazo.io.inputouput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字节数组流 - 节点流
 * 
 * <pre>
 * java.io.ByteArrayInputStream
 * java.io.ByteArrayOutputStream
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ByteArrayStreamTest extends BaseTest {

	@Test
	public void testByteArrayStream() {
		byte[] in = "12345678".getBytes();
		try (InputStream is = new ByteArrayInputStream(in); OutputStream os = new ByteArrayOutputStream(1024)) {
			/** 从流读取 */
			byte[] temp = new byte[4];
			is.read(temp);
			logger.info(new String(temp));
			is.read(temp, 0, 4);
			logger.info(new String(temp));

			/** 写入到流 */
			os.write("1234".getBytes());
			os.write(new byte[] { '5', '6', '7', '8' }, 0, 4);
			logger.info(os.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
