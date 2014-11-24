package com.jackshen.ws.activemq2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TopicSubscriberTest
{
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    private static void receive()
    {
        TopicSubscriberService topicSubscriberService = (TopicSubscriberService) appContext.getBean("topicSubscriberService");
        topicSubscriberService.receive();
    }

    public static void main(String[] args)
    {
        receive();
    }
}