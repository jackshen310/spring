package com.mycompany.activemq;

import org.apache.activemq.broker.BrokerService;
import org.junit.Test;

public class StartBroker
{
    @Test
    public void startBroker()
    {
        try
        {
            BrokerService broker = new BrokerService();
            broker.addConnector("tcp://localhost:61616");
            broker.start();
            while (true)
            {

            }
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
    }
}
