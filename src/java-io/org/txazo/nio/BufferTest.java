package org.txazo.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.nio.Buffer
 * 
 * <pre>
 * 1) ByteBuffer CharBuffer ShortBuffer IntBuffer LongBuffer FloatBuffer DoubleBuffer
 * 2) 0 <= mark <= position <= limit <= capacity
 * 3) mark() reset()
 * 4) flip() rewind()
 * 5) clear() compact()
 * </pre>
 * 
 * @author txazo
 * 
 */
public class BufferTest extends BaseTest {

	@Test
	public void testBuffer() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("D:/test/in.txt");
			FileChannel channel = fis.getChannel();
			/** 分配Buffer */
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			/** 写数据到Buffer */
			while (channel.read(buffer) != -1) {
				/** 写模式切换到读模式 */
				buffer.flip();
				/** 从Buffer读数据 */
				while (buffer.hasRemaining()) {
					logger.info("{}", (char) buffer.get());
				}
				/** 清空Buffer，准备再次写入 */
				buffer.clear();
			}
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 缓冲区分片
	 */
	@Test
	public void testBufferSlice() {
		IntBuffer buffer = IntBuffer.allocate(10);
		for (int i = 0, length = buffer.capacity(); i < length; i++) {
			buffer.put(i, i + 1);
		}

		buffer.position(3);
		buffer.limit(7);
		IntBuffer slice = buffer.slice();
		for (int i = 0, length = slice.capacity(); i < length; i++) {
			slice.put(i, slice.get(i) * 10);
		}

		buffer.position(0);
		buffer.limit(buffer.capacity());
		for (int i = 0, length = buffer.capacity(); i < length; i++) {
			System.out.print(buffer.get(i) + " ");
		}
	}

	/**
	 * 只读缓冲区
	 */
	@Test(expected = ReadOnlyBufferException.class)
	public void testReadOnlyBuffer() {
		IntBuffer buffer = IntBuffer.allocate(10);
		IntBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
		readOnlyBuffer.put(0, 1);
	}

}
