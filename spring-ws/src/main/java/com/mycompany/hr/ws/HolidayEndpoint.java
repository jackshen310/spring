package com.mycompany.hr.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

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
public class HolidayEndpoint
{

    private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

    private XPathExpression<Element> startDateExpression;

    private XPathExpression<Element> endDateExpression;

    private XPathExpression<Element> firstNameExpression;

    private XPathExpression<Element> lastNameExpression;

    private HumanResourceService humanResourceService;

    @Autowired
    public HolidayEndpoint(HumanResourceService humanResourceService) throws JDOMException
    {
        this.humanResourceService = humanResourceService;

        Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);
        XPathFactory xPathFactory = XPathFactory.instance();
        startDateExpression = xPathFactory.compile("//hr:StartDate", Filters.element(), null, namespace);
        endDateExpression = xPathFactory.compile("//hr:EndDate", Filters.element(), null, namespace);
        firstNameExpression = xPathFactory.compile("//hr:FirstName", Filters.element(), null, namespace);
        lastNameExpression = xPathFactory.compile("//hr:LastName", Filters.element(), null, namespace);
    }

    //The @PayloadRoot annotation tells Spring-WS that the handleHolidayRequest method is suitable for handling XML messages. 
    //The sort of message that this method can handle is indicated by the annotation values, 
    //in this case, it can handle XML elements that have the HolidayRequest local part and the http://mycompany.com/hr/schemas namespace.
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
    //The handleHolidayRequest(..) method is the main handling method method, which gets passed with the <HolidayRequest/> element from the incoming XML message. 
    //The @RequestPayload annotation indicates that the holidayRequest parameter should be mapped to the payload of the request message.
    //We use the XPath expressions to extract the string values from the XML messages, 
    //and convert these values to Date objects using a SimpleDateFormat (the parseData method).
    public void handleHolidayRequest(@RequestPayload Element holidayRequest) throws Exception
    {
        Date startDate = parseDate(startDateExpression, holidayRequest);
        Date endDate = parseDate(endDateExpression, holidayRequest);
        String name = firstNameExpression.evaluateFirst(holidayRequest).getText() + " " + lastNameExpression.evaluateFirst(holidayRequest).getText();

        humanResourceService.bookHoliday(startDate, endDate, name);
    }

    private Date parseDate(XPathExpression<Element> expression, Element element) throws ParseException
    {
        Element result = expression.evaluateFirst(element);
        if (result != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(result.getText());
        }
        else
        {
            throw new IllegalArgumentException("Could not evaluate [" + expression + "] on [" + element + "]");
        }
    }

}