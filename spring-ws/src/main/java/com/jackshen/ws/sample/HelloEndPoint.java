package com.jackshen.ws.sample;

import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.Text;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloEndPoint
{

    /**
     * Namespace of both request and response.
     */
    public static final String NAMESPACE_URI = "http://www.fuxueliang.com/ws/hello";

    /**
     * The local name of the expected request.
     */
    public static final String HELLO_REQUEST_LOCAL_NAME = "HelloRequest";

    /**
     * The local name of the created response.
     */
    public static final String HELLO_RESPONSE_LOCAL_NAME = "HelloResponse";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = HELLO_REQUEST_LOCAL_NAME)
    @ResponsePayload
    public Element test(@RequestPayload Element requestElement) throws Exception
    {
        //Handle Request Message.
        System.out.println("samle---" + requestElement.getText());

        //Return Response Message.
        Element element = new Element(HELLO_RESPONSE_LOCAL_NAME);
        Content c = new Text("Hello, Rondy.F!");
        element.addContent(c);
        element.setNamespace(Namespace.getNamespace(NAMESPACE_URI));
        return element;
    }

}