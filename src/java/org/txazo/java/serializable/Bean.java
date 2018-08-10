package org.txazo.java.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * <pre>
 * 1) 实现Serializable接口
 * 2) 生成serialVersionUID
 * 3) 空的构造方法
 * </pre>
 */
public class Bean implements Externalizable {

	private static final long serialVersionUID = 3730629058270665010L;

	private String userName;
	private String password;
	private String remark;

	public Bean() {
	}

	public Bean(String userName, String password, String remark) {
		this.userName = userName;
		this.password = password;
		this.remark = remark;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(userName);
		out.writeObject(password);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		userName = (String) in.readObject();
		password = (String) in.readObject();
	}

	@Override
	public String toString() {
		return "Bean [userName=" + userName + ", password=" + password + ", remark=" + remark + "]";
	}

}
