package com.mycompany.ws;

import static org.springframework.ws.test.server.RequestCreators.withPayload;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
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
import org.springframework.ws.test.server.ResponseActions;
import org.springframework.ws.test.server.ResponseMatchers;

import com.mycompany.hr.bean.Reply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-ws-servlet.xml")
public class HolidayEndpointTest2
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
    public void _01test()
    {
        try
        {
            Reply r = new Reply(true);

            JAXBContext jc = JAXBContext.newInstance(Reply.class);
            Marshaller u = jc.createMarshaller();
            u.marshal(r, System.out);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void customerEndpoint() throws Exception
    {
        Source requestPayload = readFileAsString("HolidayRequest2.xml");
        //  mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));

        ResponseActions response = mockClient.sendRequest(withPayload(requestPayload)).andExpect(ResponseMatchers.payload(readFileAsString("Reply.xml")));
        System.out.println(response);
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