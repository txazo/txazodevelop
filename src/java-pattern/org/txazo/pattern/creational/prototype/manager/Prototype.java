package org.txazo.pattern.creational.prototype.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Java对象流实现的深度克隆
 * 
 * @author tuxiaozhou
 * 
 */
public class Prototype implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final Prototype clone() {
		Prototype clone = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayInputStream bais = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			bais = new ByteArrayInputStream(baos.toByteArray());
			ois = new ObjectInputStream(bais);

			clone = (Prototype) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

		return clone;
	}

}
