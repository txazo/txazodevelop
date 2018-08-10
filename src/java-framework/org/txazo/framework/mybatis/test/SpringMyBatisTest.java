package org.txazo.framework.mybatis.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.txazo.framework.mybatis.bean.Product;
import org.txazo.framework.mybatis.dao.ProductMapper;
import org.txazo.test.base.SpringBaseTest;

public class SpringMyBatisTest extends SpringBaseTest {

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void testInsertProduct() {
		Product product = new Product("Apple", 5);
		productMapper.insertProduct(product);
		logger.info("id: {}", product.getId());
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setPrice(10);
		productMapper.updateProduct(product);
	}

	@Test
	public void testSelectProduct() {
		Product product = productMapper.selectProduct(1L);
		Assert.assertEquals("Apple", product.getName());
	}

}
