package org.txazo.io.inputouput;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字节缓冲流 - 过滤流
 * 
 * <pre>
 * java.io.BufferedInputStream
 * java.io.BufferedOutputStream
 * </pre>
 * 
 * @author txazo
 * 
 */
public class BufferedStreamTest extends BaseTest {

	@Test
	public void testBufferedStream() {
		File in = new File("D:/test/in.txt");
		File out = new File("D:/test/out.txt");
		try (InputStream is = new BufferedInputStream(new FileInputStream(in)); OutputStream os = new BufferedOutputStream(new FileOutputStream(out))) {
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
