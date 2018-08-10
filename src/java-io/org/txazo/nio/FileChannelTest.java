package org.txazo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 文件通道
 * 
 * <pre>
 * 1) java.nio.channels.FileChannel
 * 2) FileChannel channel = FileInputStream.getChannel()
 *    FileChannel channel = FileOutputStream.getChannel()
 *    FileChannel channel = RandomAccessFile.getChannel()
 * </pre>
 * 
 * @author txazo
 * 
 */
public class FileChannelTest extends BaseTest {

	@Test
	public void testFileChannel() throws IOException {
		RandomAccessFile accessFile = new RandomAccessFile("D:/test/in.txt", "r");
		FileChannel channel = accessFile.getChannel();
		int length = -1;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while ((length = channel.read(buffer)) != -1) {
			buffer.flip();
			logger.info("{}", new String(buffer.array(), 0, length));
			buffer.clear();
		}
		channel.close();
		accessFile.close();
	}

	/**
	 * 文件复制
	 */
	@Test
	public void testFileCopy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream("D:/test/in.txt");
			fos = new FileOutputStream("D:/test/out.txt");

			FileChannel finChannel = fis.getChannel();
			FileChannel fouChannel = fos.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(100);
			while (finChannel.read(buffer) != -1) {
				buffer.flip();
				fouChannel.write(buffer);
				buffer.clear();
			}

			finChannel.close();
			fouChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件锁定
	 */
	@Test
	public void testFileLock() {
		RandomAccessFile accessFile = null;
		try {
			accessFile = new RandomAccessFile("D:/test/in.txt", "rw");
			FileChannel fileChannel = accessFile.getChannel();
			FileLock lock = fileChannel.lock(0, 10, false);
			ByteBuffer buffer = ByteBuffer.allocate(10);
			fileChannel.read(buffer);
			for (int i = 0, length = buffer.capacity(); i < length; i++) {
				buffer.put(i, (byte) (buffer.get(i) + 1));
			}
			buffer.flip();
			fileChannel.position(0);
			fileChannel.write(buffer);
			lock.release();
			fileChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (accessFile != null) {
				try {
					accessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testTransferFrom() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("D:/test/in.txt", "r");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("D:/test/out.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		toChannel.transferFrom(fromChannel, 0, fromChannel.size());

		toChannel.close();
		toFile.close();
		fromChannel.close();
		fromFile.close();
	}

	@Test
	public void testTransferTo() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("D:/test/in.txt", "r");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("D:/test/out.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		fromChannel.transferTo(0, fromChannel.size(), toChannel);

		toChannel.close();
		toFile.close();
		fromChannel.close();
		fromFile.close();
	}

}
