package org.txazo.io.inputouput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 数据流 - 过滤流
 * 
 * <pre>
 * java.io.DataInputStream
 * java.io.DataOutputStream
 * </pre>
 * 
 * @author txazo
 * 
 */
public class DataStreamTest extends BaseTest {

	@Test
	public void testDataStream() {
		File file = new File("D:/test/in.txt");
		try (DataInputStream dis = new DataInputStream(new FileInputStream(file));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
			dos.writeInt(1);
			dos.writeBoolean(true);
			dos.writeDouble(12.34);

			logger.info("{}", dis.readInt());
			logger.info("{}", dis.readBoolean());
			logger.info("{}", dis.readDouble());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
