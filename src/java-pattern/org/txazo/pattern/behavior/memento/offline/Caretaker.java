package org.txazo.pattern.behavior.memento.offline;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;

/**
 * 备忘录模式
 * 
 * <pre>
 * 1. 文件离线存储备忘录
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Caretaker {

	/** 从文件获取备忘录 */
	public Memento getMemento() {
		Memento memento = null;
		ObjectInputStream bis = null;
		try {
			bis = new ObjectInputStream(new BufferedInputStream(new FileInputStream("memento.txt")));
			memento = (Memento) bis.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bis);
		}
		return memento;
	}

	/** 保存备忘录到文件 */
	public void saveMemento(Memento memento) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("memento.txt")));
			oos.writeObject(memento);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(oos);
		}
	}

}
