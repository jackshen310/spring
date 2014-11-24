package com.jackshen.ws.activemq;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.JmsException;

public class SimpleJMSReceiver
{
    private static final Log logger = LogFactory.getLog(SimpleJMSReceiver.class);

    public static void main(String[] args) throws InterruptedException
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/jackshen/ws/activemq/jms-receiver.xml");
        while (true)
        {
            Thread.sleep(100);
        }
    }

    public void receive(TextMessage message) throws JmsException, JMSException
    {
        logger.info(message.getText());
    }
}
