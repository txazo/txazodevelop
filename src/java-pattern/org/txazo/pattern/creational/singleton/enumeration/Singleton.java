package org.txazo.pattern.creational.singleton.enumeration;

/**
 * 枚举单例
 * 
 * @author txazo
 * 
 */
public enum Singleton {

	INSTANCE {

		@SuppressWarnings("unused")
		public String show() {
			return "Enum Singleton";
		}

	};

}
