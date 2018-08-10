package org.txazo.jms.jms.java;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.txazo.test.base.BaseTest;

public class MessageSenderTest extends BaseTest {

	public void testMessageSender() throws Exception {
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, "tcp://112.124.6.220:61616");
		Context context = new InitialContext(props);
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Destination dest = (Destination) context.lookup("/queue/myjms");
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(dest);
		Message message = session.createTextMessage("JMS Message");
		producer.send(message);
		session.close();
		connection.close();
	}

}
