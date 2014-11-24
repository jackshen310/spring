package com.mycompany.hr.ws;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mycompany.hr.bean.Holiday2Request;
import com.mycompany.hr.bean.Reply;
import com.mycompany.hr.service.HumanResourceService;

/**
 * 
 * @ClassName: HolidayEndpoint
 * @Description: TODO
 * @author jackshen
 * @Date: 2014-11-9 下午12:21:53
 * 
 */
@Endpoint
//The HolidayEndpoint is annotated with @Endpoint.
//This marks the class as a special sort of @Component, suitable for handling XML messages in Spring-WS,
//and also making it eligible for suitable for component scanning                                                                                                            (1)
public class HolidayEndpoint2
{

    private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

    private HumanResourceService humanResourceService;

    @Autowired
    public HolidayEndpoint2(HumanResourceService humanResourceService) throws JDOMException
    {
        this.humanResourceService = humanResourceService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Holiday2Request")
    @ResponsePayload
    public Reply handleHolidayRequest(@RequestPayload Holiday2Request holidayRequest) throws Exception
    {
        System.out.println(holidayRequest.getHoliday().getStartDate());
        return new Reply(true);

    }

}