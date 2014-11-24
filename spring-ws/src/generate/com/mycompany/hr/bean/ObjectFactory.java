//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.11.12 时间 11:16:48 PM CST 
//


package com.mycompany.hr.bean;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mycompany.hr.bean package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mycompany.hr.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HolidayRequest }
     * 
     */
    public HolidayRequest createHolidayRequest() {
        return new HolidayRequest();
    }

    /**
     * Create an instance of {@link HolidayType }
     * 
     */
    public HolidayType createHolidayType() {
        return new HolidayType();
    }

    /**
     * Create an instance of {@link EmployeeType }
     * 
     */
    public EmployeeType createEmployeeType() {
        return new EmployeeType();
    }

    /**
     * Create an instance of {@link Holiday2Request }
     * 
     */
    public Holiday2Request createHoliday2Request() {
        return new Holiday2Request();
    }

    /**
     * Create an instance of {@link ReplyResponse }
     * 
     */
    public ReplyResponse createReplyResponse() {
        return new ReplyResponse();
    }

}
