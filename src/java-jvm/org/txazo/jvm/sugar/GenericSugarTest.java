package org.txazo.jvm.sugar;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 泛型 - 语法糖
 * 
 * <pre>
 * 1) Java的泛型只在源代码中存在，供编辑器检查使用
 * 2) 编译后的字节码文件已擦除了泛型类型，同时在必要的地方插入了强制转型的代码
 * </pre>
 */
public class GenericSugarTest extends BaseTest {

	/**
	 * 泛型
	 */
	@Test
	public void testGeneric() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "txazo");
		String txazo = map.get(1);
		logger.info("{}", txazo);
	}

	/**
	 * 泛型 - 反编译
	 */
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGenericDeCompile() {
		Map map = new HashMap();
		map.put(Integer.valueOf(1), "txazo");
		String txazo = (String) map.get(Integer.valueOf(1));
		logger.info("{}", txazo);
	}

	/**
	 * 下面两个方法不能通过编译
	 * 
	 * <pre>
	 * 1) Class文件中不能存在特征签名相同的方法
	 * 2) Java代码中的方法特征签名只包括了方法名称、参数顺序和参数类型，并不包括方法的返回值
	 * 3) List<String>和List<Integer>编译后都变成了原生类型List
	 * </pre>
	 */

	// public int method(List<String> list) {
	// return 1;
	// }
	//
	// public boolean method(List<Integer> list) {
	// return true;
	// }

}
