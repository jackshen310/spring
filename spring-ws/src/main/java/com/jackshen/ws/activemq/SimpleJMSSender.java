package com.jackshen.ws.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @see http://nettm.blog.51cto.com/4841905/1160108
 * @ClassName: SimpleJMSSender
 * @Description: TODO
 * @author jackshen
 * @Date: 2014-11-19 下午9:26:22
 * 
 */
public class SimpleJMSSender
{
    private static final Log logger = LogFactory.getLog(SimpleJMSSender.class);

    static int index = 0;

    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/jackshen/ws/activemq/jms-sender.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("myJmsTemplate");
        for (int i = 0; i < 100; i++)
        {
            index = i;
            try
            {
                jmsTemplate.send(new MessageCreator()
                {
                    public Message createMessage(Session session) throws JMSException
                    {
                        TextMessage msg = session.createTextMessage();
                        // 设置消息属性 
                        msg.setStringProperty("phrCode", "C001  " + index);
                        // 设置消息内容 
                        msg.setText("Hello World!  " + index);
                        logger.info("Send  " + index);
                        return msg;
                    }
                });
            }
            catch (JmsException e)
            {
                System.exit(1);
            }
        }
    }
}
