package org.txazo.jvm.sugar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.txazo.test.base.BaseTest;

/**
 * foreach - 语法糖
 * 
 */
public class ForeachSugarTest extends BaseTest {

	/**
	 * foreach
	 */
	public void testForeach() {
		List<Integer> list = new ArrayList<>();
		for (Integer i : list) {
			logger.info("{}", i);
		}
	}

	/**
	 * foreach - 反编译
	 */
	@SuppressWarnings({ "rawtypes" })
	public void testForeachDeCompile() {
		List list = new ArrayList();
		Integer i;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); logger.info("{}", i)) {
			i = (Integer) iterator.next();
		}
	}

}
