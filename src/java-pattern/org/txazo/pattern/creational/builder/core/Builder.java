package org.txazo.pattern.creational.builder.core;

/**
 * 生成器接口
 * 
 * @author txazo
 * 
 */
public interface Builder {

	public Product getProduct();

	public void buildHeader();

	public void buildBody();

	public void buildFooter();

}
