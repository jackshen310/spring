package com.jackshen.ws.activemq3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueProducerTest
{
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext("com/jackshen/ws/activemq3/applicationContext.xml");

    private static void send()
    {
        QueueProducerService producerService = (QueueProducerService) appContext.getBean("queueProducerService");
        producerService.send();
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        send();
    }

}