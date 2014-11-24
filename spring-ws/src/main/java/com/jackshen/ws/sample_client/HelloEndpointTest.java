package com.jackshen.ws.sample_client;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;

import com.jackshen.ws.sample_client.bean.HelloRequest;
import com.jackshen.ws.sample_client.bean.HelloResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "spring-ws-servlet.xml")
public class HelloEndpointTest
{

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient()
    {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    //@Test
    public void customerEndpoint() throws Exception
    {
        /**
         * <?xml version="1.0" encoding="UTF-8"?>
         * <HelloRequest xmlns="http://www.fuxueliang.com/ws/hello">
         * Rondy.F
         * </HelloRequest>
         */
        Source requestPayload = readFileAsString("HelloRequest.xml");

        mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(readFileAsString("HelloResponse.xml")));

    }

    @Test
    public void test()
    {
        com.jackshen.ws.sample_client.bean.ObjectFactory objectFactory = new com.jackshen.ws.sample_client.bean.ObjectFactory();
        HelloRequest request = objectFactory.createHelloRequest();
        request.setHello("Rondy.F");

        HelloResponse response = (HelloResponse) webServiceTemplate.marshalSendAndReceive(request);

        Assert.assertEquals("Hello, Rondy.F!", response.getHello());
    }

    public StreamSource readFileAsString(String file)
    {
        return new StreamSource(this.getClass().getResourceAsStream(file));

    }
}