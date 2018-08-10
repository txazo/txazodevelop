package org.txazo.framework.mybatis.dao;

import org.txazo.framework.mybatis.bean.Product;

public interface ProductMapper {

	public void insertProduct(Product product);

	public void updateProduct(Product product);

	public Product selectProduct(Long id);

}
