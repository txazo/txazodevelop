package org.txazo.io.inputouput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字节文件流 - 节点流
 * 
 * <pre>
 * java.io.FileInputStream
 * java.io.FileOutputStream
 * </pre>
 * 
 * @author txazo
 * 
 */
public class FileStreamTest extends BaseTest {

	@Test
	public void testFileStream() {
		File in = new File("D:/test/in.txt");
		File out = new File("D:/test/out.txt");
		try (InputStream is = new FileInputStream(in); OutputStream os = new FileOutputStream(out)) {
			int length = -1;
			byte[] temp = new byte[1024];
			/** 读取文件 */
			while ((length = is.read(temp)) != -1) {
				/** 写入文件 */
				os.write(temp, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
