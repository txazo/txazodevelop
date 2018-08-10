package org.txazo.java.jdbc.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JdbcCommonUtil {

	public static void executeUpdate(String sql, Object[] params) {
		Connection connection = JDBCConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i + 1, params[i]);
				}
			}

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static void batchUpdate(List<String> sqls) {
		Connection connection = JDBCConnectionManager.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			int size = 0;
			if (sqls != null && (size = sqls.size()) > 0) {
				for (int i = 0; i < size; i++) {
					statement.addBatch(sqls.get(i));
				}
			}
			statement.executeBatch();
		} catch (SQLException e) {
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static void batchUpdate(String sql, List<String[]> params) {
		Connection connection = JDBCConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);

			int size = 0;
			if (params != null && (size = params.size()) > 0) {
				String[] p = null;
				for (int i = 0; i < size; i++) {
					p = params.get(i);
					if (p != null && p.length > 0) {
						for (int j = 0; j < p.length; j++) {
							preparedStatement.setObject(j + 1, p[j]);
						}
						preparedStatement.addBatch();
					}
				}
				preparedStatement.executeBatch();
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static <T> List<T> executeQuery(String sql, Class<T> objectClass, Object[] params) {
		List<T> list = null;
		Connection connection = JDBCConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);

			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i + 1, params[i]);
				}
			}

			resultSet = preparedStatement.executeQuery();

			list = getResultList(objectClass, resultSet);

			return list;
		} catch (Exception e) {
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	private static <T> List<T> getResultList(Class<T> objectClass, ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			Map<String, Method> map = new HashMap<String, Method>();
			Field[] fields = objectClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				Method setMethod = objectClass.getMethod(setMethodName, new Class[] { fields[i].getType() });
				map.put(fieldName, setMethod);
			}

			Set<String> keySet = map.keySet();

			while (resultSet.next()) {
				T object = objectClass.newInstance();
				for (String key : keySet) {
					map.get(key).invoke(object, resultSet.getObject(key.toLowerCase()));
				}
				list.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
