package com.mycompany.hr.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://mycompany.com/hr/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reply
{
    public boolean agree;

    public Reply(boolean agree)
    {
        super();
        this.agree = agree;
    }

    public Reply()
    {

    }

    public boolean isAgree()
    {
        return agree;
    }

    public void setAgree(boolean agree)
    {
        this.agree = agree;
    }

}
