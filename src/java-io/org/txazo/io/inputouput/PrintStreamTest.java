package org.txazo.io.inputouput;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字节打印流 - 过滤流
 * 
 * <pre>
 * java.io.PrintStream
 * </pre>
 * 
 * @author txazo
 * 
 */
public class PrintStreamTest extends BaseTest {

	@Test
	public void testPrintStream() {
		try (PrintStream ps = new PrintStream("D:/test/in.txt")) {
			ps.print(12);
			ps.println("34");
			System.setOut(ps);
			System.out.println("56");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
