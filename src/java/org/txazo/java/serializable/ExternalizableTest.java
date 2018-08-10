package org.txazo.java.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.io.Externalizable 序列化
 * 
 * <pre>
 * 控制序列化和反序列化过程
 * </pre>
 */
public class ExternalizableTest extends BaseTest {

	@Test
	public void testExternalizable() throws IOException, ClassNotFoundException {
		String fileName = "D:/test/object.txt";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(new Bean("root", "123456", "root"));
		IOUtils.closeQuietly(oos);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		Bean bean = (Bean) ois.readObject();
		logger.info("{}", bean);
		IOUtils.closeQuietly(ois);
	}

}
