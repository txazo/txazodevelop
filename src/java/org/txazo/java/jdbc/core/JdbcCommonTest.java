package org.txazo.java.jdbc.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.txazo.java.jdbc.core.bean.User;
import org.txazo.test.base.BaseTest;

public class JdbcCommonTest extends BaseTest {

	private Logger logger = Logger.getLogger(JdbcCommonTest.class);

	@Test
	public void testUpdate() {
		JdbcCommonUtil.executeUpdate("truncate table User", null);
		JdbcCommonUtil.executeUpdate("insert into User(user, password) values(?, ?)", new Object[] { "root", "root" });
		JdbcCommonUtil.executeUpdate("insert into User(user, password) values(?, ?)", new Object[] { "admin", "admin" });
		JdbcCommonUtil.executeUpdate("insert into User(user, password) values(?, ?)", new Object[] { "manager", "manager" });
	}

	@Test
	public void testBatchUpdate() {
		List<String> sqls = new ArrayList<String>();
		sqls.add("truncate table User");
		sqls.add("insert into User(user, password) values('resin', 'resin')");
		sqls.add("insert into User(user, password) values('tomcat', 'tomcat')");
		JdbcCommonUtil.batchUpdate(sqls);

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "jetty", "jetty" });
		params.add(new String[] { "jboss", "jboss" });
		JdbcCommonUtil.batchUpdate("insert into User(user, password) values(?, ?)", params);
	}

	@Test
	public void testQuery() {
		List<User> list = JdbcCommonUtil.executeQuery("select * from User", User.class, null);
		if (list != null) {
			User user = null;
			for (int i = 0, size = list.size(); i < size; i++) {
				user = list.get(i);
				logger.info(user.getId() + "\t" + user.getUser() + "\t" + user.getPassword());
			}
		}
	}

}
