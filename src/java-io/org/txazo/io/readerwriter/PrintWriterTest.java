package org.txazo.io.readerwriter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符打印流 - 过滤流
 * 
 * <pre>
 * java.io.PrintWriter
 * </pre>
 * 
 * @author txazo
 * 
 */
public class PrintWriterTest extends BaseTest {

	@Test
	public void testPrintWriter() {
		try (PrintWriter pw = new PrintWriter("D:/test/in.txt")) {
			pw.print(1);
			pw.print("234");
			pw.println("5678");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
