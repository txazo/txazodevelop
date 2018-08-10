package org.txazo.framework.c3P0.jdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0 JDBC连接工具类
 * 
 * @author txazo
 * 
 */
public class C3P0ConnectionManager {

	private static ComboPooledDataSource dataSource = null;
	/** classpath: c3p0-config.xml */
	private static Map<String, ComboPooledDataSource> dataSourceMap = new HashMap<String, ComboPooledDataSource>();

	static {
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://112.124.6.220/test");
			dataSource.setUser("txazo");
			dataSource.setPassword("txazo");
			dataSource.setMaxPoolSize(20);
			dataSource.setMinPoolSize(2);
			dataSource.setInitialPoolSize(4);

			dataSourceMap.put("testdb", new ComboPooledDataSource("testdb"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private C3P0ConnectionManager() {
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

	public static synchronized Connection getConnection(String db) {
		ComboPooledDataSource dataSource = dataSourceMap.get(db);
		if (dataSource != null) {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
		return null;
	}

}
