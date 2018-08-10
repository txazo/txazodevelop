package org.txazo.framework.commonsio;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FilenameUtilsTest {

	@Test
	public void testNormalize() {
		System.out.println(FilenameUtils.normalize("D:/commons/io/file.xml"));
		System.out.println(FilenameUtils.normalizeNoEndSeparator("D:/commons/io/file.xml/"));
	}

}
