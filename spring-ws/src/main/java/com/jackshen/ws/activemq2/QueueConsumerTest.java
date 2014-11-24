package com.jackshen.ws.activemq2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueConsumerTest
{
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext("com/jackshen/ws/activemq2/applicationContext.xml");

    private static void receive()
    {
        QueueConsumerService consumerService = (QueueConsumerService) appContext.getBean("queueConsumerService");
        consumerService.receive();
    }

    public static void main(String[] args)
    {
        receive();
    }

}