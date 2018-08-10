package org.txazo.io.inputouput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 对象流 - 过滤流
 * 
 * <pre>
 * java.io.ObjectInputStream
 * java.io.ObjectOutputStream
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ObjectStreamTest extends BaseTest implements Serializable {

	private static final long serialVersionUID = 7332426014288047784L;

	@Test
	public void testObjectStream() {
		File file = new File("D:/test/object.txt");
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(new ObjectStream("root"));
			oos.writeObject(new ObjectStream("admin"));
			logger.info("{}", (ObjectStream) ois.readObject());
			logger.info("{}", (ObjectStream) ois.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class ObjectStream implements Serializable {

		private static final long serialVersionUID = 5171581647700465383L;

		private String name;

		public ObjectStream(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "ObjectStream [name=" + name + "]";
		}

	}

}
