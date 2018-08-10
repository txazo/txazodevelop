package org.txazo.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.txazo.test.base.SpringBaseTest;

public class SpringJdbcInsertTest extends SpringBaseTest {

	@Autowired
	private JdbcTemplate testJdbcTemplate;

	@Test
	public void testInsert() {
		String sql = "insert into Product(name, price, createTime) values('Apple', 10.00, now())";
		testJdbcTemplate.execute(sql);
	}

	@Test
	public void testInsertFetchId() {
		final String sql = "insert into Product(name, price, createTime) values('Orange', 10.00, now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		testJdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}

		}, keyHolder);
		logger.info("InsertFetchId id: {}", keyHolder.getKey().longValue());
	}

}
