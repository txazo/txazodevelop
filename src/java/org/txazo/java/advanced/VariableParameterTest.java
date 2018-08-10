package org.txazo.java.advanced;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 可变长参数
 */
public class VariableParameterTest extends BaseTest {

	@Test
	public void testVariableParameter() {
		logger.info("{}", VariableParameter.show());
		logger.info("{}", VariableParameter.show(1));
		logger.info("{}", VariableParameter.show(1, 2));
		logger.info("{}", VariableParameter.show(new int[] { 1, 2, 3 }));
	}

	private static class VariableParameter {

		public static String show(int... params) {
			if (params == null || params.length < 1) {
				return "";
			}
			StringBuffer sb = new StringBuffer();
			for (int i : params) {
				sb.append(i).append(",");
			}
			return sb.substring(0, sb.length() - 1);
		}

	}

}
