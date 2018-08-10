package org.txazo.framework.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.txazo.framework.mybatis.bean.Product;
import org.txazo.framework.mybatis.dao.ProductMapper;
import org.txazo.test.base.BaseTest;

public class MyBatisTest extends BaseTest {

	private SqlSession session;
	private ProductMapper productMapper;

	@Before
	public void before() throws IOException {
		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		session = factory.openSession();
		productMapper = session.getMapper(ProductMapper.class);
	}

	@After
	public void close() {
		session.commit();
		session.close();
	}

	@Test
	public void testInsertProduct() throws Exception {
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
