package org.txazo.java.lang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class CloneTest extends BaseTest {

	@Test
	public void testDeepClone() {
		User user = new User("root");
		User clone = deepClone(user);
		Assert.assertEquals("root", clone.getName());
	}

	/**
	 * 深度克隆
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepClone(T obj) {
		T cloneObj = null;
		ByteArrayOutputStream baos = null;
		ByteArrayInputStream bais = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);

			bais = new ByteArrayInputStream(baos.toByteArray());
			ois = new ObjectInputStream(bais);

			cloneObj = (T) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return cloneObj;
	}

	protected class User implements Serializable {

		private static final long serialVersionUID = -5687631680669295152L;

		private String name;

		private User(String name) {
			this.name = name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

}
