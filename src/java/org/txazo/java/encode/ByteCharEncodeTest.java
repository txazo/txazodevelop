package org.txazo.java.encode;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字节和字符的编码
 * 
 * <pre>
 * GBK: 英文字符1字节，中文字符2字节
 * UTF-8: 英文字符1字节，中文字符3到4字节
 * UTF-16: 英文字符2字节，中文字符3到4字节
 * UTF-32: 英文字符4字节，中文字符4字节
 * </pre>
 */
public class ByteCharEncodeTest extends BaseTest {

	@Test
	public void testByteCharEncode() throws UnsupportedEncodingException {
		String str = "好h";
		byteCharEncode(str, "GBK");
		byteCharEncode(str, "UTF-8");
		byteCharEncode(str, "UTF-16");
		byteCharEncode(str, "UTF-32");
	}

	public void byteCharEncode(String str, String encode) throws UnsupportedEncodingException {
		logger.info("{} byte length: {}", encode, str.getBytes(encode).length);
		logger.info("{} char length: {}", encode, str.length());
	}

}
