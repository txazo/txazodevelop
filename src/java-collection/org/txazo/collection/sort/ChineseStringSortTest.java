package org.txazo.collection.sort;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 中文字符串排序
 */
public class ChineseStringSortTest extends BaseTest {

	@Test
	public void testChineseStringSort() {
		List<String> list = new ArrayList<String>();
		list.add("txazo");
		list.add("admin");
		list.add("root");
		list.add("张三");
		list.add("李四");
		list.add("王五");
		list.add("刘六");
		Comparator<Object> comparator = Collator.getInstance(Locale.CHINA);
		Collections.sort(list, comparator);
		System.out.println(list);
	}

}
