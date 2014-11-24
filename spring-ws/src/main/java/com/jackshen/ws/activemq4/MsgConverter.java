package com.jackshen.ws.activemq4;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class MsgConverter implements MessageConverter
{

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException
    {
        if (!(message instanceof TextMessage))
        {
            throw new MessageConversionException("Message is not TextMessage");
        }
        System.out.println("--转换接收的消息--");
        TextMessage textMessage = (TextMessage) message;
        MsgPoJo msgPojo = new MsgPoJo();
        String[] texts = textMessage.getText().split(",");
        msgPojo.setId(texts[0]);
        msgPojo.setText(texts[1]);
        return msgPojo;
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException
    {
        if (!(object instanceof MsgPoJo))
        {
            throw new MessageConversionException("obj is not MsgPojo");
        }
        System.out.println("--转换发送的消息--");
        MsgPoJo msgPojo = (MsgPoJo) object;
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(msgPojo.getId() + "," + msgPojo.getText());
        return textMessage;
    }
}