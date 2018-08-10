package org.txazo.pattern.creational.builder.core;

/**
 * 生成器模式
 * 
 * <pre>
 * 1. 分离对象的表示和构建，使得同样的构建可以创建不同的表示
 * 2. 统一对象的构建过程，可构建复杂对象
 * 3. 分离整体构建算法和部件构造
 * 4. 解耦过程和部件
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Director {

	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	public void build() {
		builder.buildHeader();
		builder.buildBody();
		builder.buildFooter();
	}

	public Product getProduct() {
		return builder.getProduct();
	}

}
