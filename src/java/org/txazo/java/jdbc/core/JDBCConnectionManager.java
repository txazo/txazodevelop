package org.txazo.java.jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionManager {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://112.124.6.220:3306/test";
	private static final String USER = "txazo";
	private static final String PASSWORD = "txazo";

	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JDBCConnectionManager() {
	}

	public static synchronized Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
