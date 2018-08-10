package org.txazo.pattern.structural.decorator.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IOTest {

	/**
	 * IO流装饰器
	 * 
	 * <pre>
	 * 1. 抽象装饰器：
	 *    FilterInputStream FilterOutputStream
	 * 2. 具体装饰器：
	 *    BufferedInputStream BufferedOutputStream
	 *    ObjectInputStream ObjectOutputStream
	 *    DataInputStream DataOutputStream
	 *    BufferedWriter BufferedReader
	 * 3. 可装饰IO流：
	 *    FileInputStream FileOutputStream
	 *    ByteArrayInputStream ByteArrayOutputStream
	 *    StringBufferInputStream StringBufferOutputStream
	 * </pre>
	 * 
	 * @throws IOException
	 */
	@Test
	public void testIO() throws IOException {
		File file = new File("D:/io.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		writer.write("IODecorator");
		IOUtils.closeQuietly(writer);

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = reader.readLine();
		System.out.println(line);
		IOUtils.closeQuietly(reader);

		file.delete();
	}

	/**
	 * IO流过滤装饰器
	 * 
	 * @throws IOException
	 */
	@Test
	public void testIOFilter() throws IOException {
		File file = new File("D:/io.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new EncryptOutputStream(new FileOutputStream(file))));
		writer.write("IOFilter");
		IOUtils.closeQuietly(writer);

		BufferedReader reader = new BufferedReader(new InputStreamReader(new EncryptInputStream(new FileInputStream(file))));
		System.out.println(reader.readLine());
		IOUtils.closeQuietly(reader);

		file.delete();
	}
}
