package com.jackshen.ws.activemq4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueProducerTest
{
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext("com/jackshen/ws/activemq4/applicationContext.xml");

    private static void send()
    {
        QueueProducerService producerService = (QueueProducerService) appContext.getBean("queueProducerService");
        producerService.send();
    }

    private static void convertAndSend()
    {
        QueueProducerService producerService = (QueueProducerService) appContext.getBean("queueProducerService");
        producerService.convertAndSend();
    }

    public static void main(String[] args)
    {
        //send();  
        convertAndSend();
    }

}