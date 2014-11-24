package com.jackshen.ws.sample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

public class HelloWebServiceClient
{

    public static final String NAMESPACE_URI = "http://www.fuxueliang.com/ws/hello";

    public static final String PREFIX = "tns";

    private SOAPConnectionFactory connectionFactory;

    private MessageFactory messageFactory;

    private URL url;

    public HelloWebServiceClient(String url) throws SOAPException, MalformedURLException
    {
        connectionFactory = SOAPConnectionFactory.newInstance();
        messageFactory = MessageFactory.newInstance();
        this.url = new URL(url);
    }

    private SOAPMessage createHelloRequest() throws SOAPException
    {
        SOAPMessage message = messageFactory.createMessage();
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        Name helloRequestName = envelope.createName("HelloRequest", PREFIX, NAMESPACE_URI);
        SOAPBodyElement helloRequestElement = message.getSOAPBody().addBodyElement(helloRequestName);
        helloRequestElement.setValue("Rondy.F");
        return message;
    }

    public void callWebService() throws SOAPException, IOException
    {
        SOAPMessage request = createHelloRequest();
        SOAPConnection connection = connectionFactory.createConnection();
        SOAPMessage response = connection.call(request, url);
        if (!response.getSOAPBody().hasFault())
        {
            writeHelloResponse(response);
        }
        else
        {
            SOAPFault fault = response.getSOAPBody().getFault();
            System.err.println("Received SOAP Fault");
            System.err.println("SOAP Fault Code :" + fault.getFaultCode());
            System.err.println("SOAP Fault String :" + fault.getFaultString());
        }
    }

    @SuppressWarnings("unchecked")
    private void writeHelloResponse(SOAPMessage message) throws SOAPException
    {
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        Name helloResponseName = envelope.createName("HelloResponse", PREFIX, NAMESPACE_URI);
        Iterator childElements = message.getSOAPBody().getChildElements(helloResponseName);
        SOAPBodyElement helloResponseElement = (SOAPBodyElement) childElements.next();
        String value = helloResponseElement.getTextContent();
        System.out.println("Hello Response [" + value + "]");
    }

    public static void main(String[] args) throws Exception
    {
        String url = "http://localhost:8080/springws";
        HelloWebServiceClient helloClient = new HelloWebServiceClient(url);
        helloClient.callWebService();
    }
}