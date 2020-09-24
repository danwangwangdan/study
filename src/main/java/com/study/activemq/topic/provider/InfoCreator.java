package com.study.activemq.topic.provider;

import com.common.model.Information;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class InfoCreator implements MessageCreator {

    private Information information;

    public InfoCreator(Information information) {
        this.information = information;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createObjectMessage(information);
    }

}
