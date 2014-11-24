package com.jackshen.ws.activemq4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueConsumerTest
{
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext("com/jackshen/ws/activemq4/applicationContext.xml");

    private static void receive()
    {
        QueueConsumerService consumerService = (QueueConsumerService) appContext.getBean("queueConsumerService");
        consumerService.receive();
    }

    private static void receiveAndConvert()
    {
        QueueConsumerService consumerService = (QueueConsumerService) appContext.getBean("queueConsumerService");
        consumerService.receiveAndConvert();
    }

    public static void main(String[] args)
    {
        //receive();  
        receiveAndConvert();
    }
}