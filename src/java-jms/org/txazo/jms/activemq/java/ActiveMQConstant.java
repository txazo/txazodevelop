package org.txazo.jms.activemq.java;

import org.apache.activemq.ActiveMQConnection;

public final class ActiveMQConstant {

	public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	public static final String BROKER_URL = "tcp://127.0.0.1:61616";
	public static final String SUBJECT = "mqqueue";

}
