package org.txazo.io.readerwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * 字节字符转换流 - 过滤流
 * 
 * <pre>
 * java.io.InputStreamReader
 * java.io.OutputStreamWriter
 * </pre>
 * 
 * @author txazo
 * 
 */
public class InputOuputReaderWriterTest {

	@Test
	public void testInputOuputReaderWriter() {
		File in = new File("D:/test/in.txt");
		File out = new File("D:/test/out.txt");
		/** 可指定编码 */
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(in), "utf-8"));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "utf-8"))) {
			String data = null;
			while ((data = br.readLine()) != null) {
				bw.write(data);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
