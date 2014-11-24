package com.mycompany.ws;

import static org.springframework.ws.test.server.RequestCreators.withPayload;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-ws-servlet.xml")
public class HolidayEndpointTest
{

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient()
    {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void customerEndpoint() throws Exception
    {
        Source requestPayload = readFileAsString("HolidayRequest.xml");
        //  mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
        mockClient.sendRequest(withPayload(requestPayload));
    }

    public StreamSource readFileAsString(String file)
    {
        ClassPathResource resource = new ClassPathResource(file);
        try
        {
            return new StreamSource(resource.getFile());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}