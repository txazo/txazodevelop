package org.txazo.jvm.sugar;

import org.txazo.test.base.BaseTest;

/**
 * 枚举 - 语法糖
 * 
 * <pre>
 * 1) 枚举在编译期会被编译为普通类
 * </pre>
 */
public class EnumSugarTest extends BaseTest {
}

/**
 * 枚举
 */
enum EnumClass {
	RED, GREEN
}

/**
 * 枚举 - 反编译
 */
//final class EnumClassDeCompile extends Enum {
//
//	private EnumClassDeCompile(String s, int i) {
//		super(s, i);
//	}
//
//	public static EnumClassDeCompile[] values() {
//		EnumClassDeCompile aenumclassdecompile[];
//		int i;
//		EnumClassDeCompile aenumclassdecompile1[];
//		System.arraycopy(aenumclassdecompile = ENUM$VALUES, 0, aenumclassdecompile1 = new EnumClassDeCompile[i = aenumclassdecompile.length], 0, i);
//		return aenumclassdecompile1;
//	}
//
//	public static EnumClassDeCompile valueOf(String s) {
//		return (EnumClassDeCompile) Enum.valueOf(EnumClassDeCompile.class, s);
//	}
//
//	public static final EnumClassDeCompile RED;
//	public static final EnumClassDeCompile GREEN;
//	private static final EnumClassDeCompile ENUM$VALUES[];
//
//	static {
//		RED = new EnumClassDeCompile("RED", 0);
//		GREEN = new EnumClassDeCompile("GREEN", 1);
//		ENUM$VALUES = (new EnumClassDeCompile[] { RED, GREEN });
//	}
//
//}