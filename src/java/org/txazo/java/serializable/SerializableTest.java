package org.txazo.java.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.io.Serializable 序列化
 * 
 * <pre>
 * 1) 实现Serializable接口
 * 2) 生成serialVersionUID
 * </pre>
 * 
 * <pre>
 * 序列化应用
 * 1) 本地保存(文件 内存)
 * 2) 网络传输(RPC Memcached)
 * </pre>
 */
public class SerializableTest extends BaseTest implements Serializable {

	private static final long serialVersionUID = -2857457675896393110L;

	@Test
	public void testSerializable() throws IOException, ClassNotFoundException {
		String fileName = "D:/test/object.txt";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(new Bean("root", "123456", "root"));
		IOUtils.closeQuietly(oos);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		Bean bean = (Bean) ois.readObject();
		logger.info("{}", bean);
		IOUtils.closeQuietly(ois);
	}

	public class Bean implements Serializable {

		private static final long serialVersionUID = 7152835378614709310L;

		private String userName;
		private String password;
		/** transient修饰的变量不参入序列化 */
		private transient String remark;

		public Bean(String userName, String password, String remark) {
			this.userName = userName;
			this.password = password;
			this.remark = remark;
		}

		@Override
		public String toString() {
			return "Bean [userName=" + userName + ", password=" + password + ", remark=" + remark + "]";
		}

	}

}
