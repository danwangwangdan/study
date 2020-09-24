package com.study.activemq.topic.consumer;

import com.common.model.Information;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class TestListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        Information info = null;
        try {
            info = (Information) objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.err.println("收到一条消息" + info.toString());
    }
}
