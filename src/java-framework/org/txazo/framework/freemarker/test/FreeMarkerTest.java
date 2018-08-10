package org.txazo.framework.freemarker.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.txazo.framework.freemarker.util.FreeMarkerUtil;

@RunWith(JUnit4.class)
public class FreeMarkerTest {

	@Test
	public void testFreeMarker() throws Exception {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("name", "txazo");
		String html = FreeMarkerUtil.getHtml(valueMap, "template/mail.ftl");
		System.out.println(html);
	}

}
