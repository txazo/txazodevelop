package org.txazo.framework.dbcp.jdbc;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * DBCP JDBC连接工具类
 * 
 * @author txazo
 * 
 */
public class DBCPConnectionManager {

	private static BasicDataSource dataSource = null;

	static {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1/txazo");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setInitialSize(4);
	}

	private DBCPConnectionManager() {
	}

	public static synchronized Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
