package org.txazo.framework.dbcp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestDBCP {

	@Test
	public void testDBCP() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DBCPConnectionManager.getConnection();
			statement = connection.prepareStatement("select * from user");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t"
						+ resultSet.getString("name") + "\t"
						+ resultSet.getInt("clickcount"));
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
