package org.txazo.framework.c3P0.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class C3P0Test extends BaseTest {

	@Test
	public void testC3P01() {
		testDb(C3P0ConnectionManager.getConnection());
	}

	@Test
	public void testC3P02() {
		testDb(C3P0ConnectionManager.getConnection("testdb"));
	}

	public void testDb(Connection connection) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement("select * from User");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				logger.info(resultSet.getInt("id") + "\t" + resultSet.getString("user") + "\t" + resultSet.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
