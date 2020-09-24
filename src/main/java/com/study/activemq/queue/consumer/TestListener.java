package com.study.activemq.queue.consumer;

import com.common.model.Information;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Logger;

/**
 * @描述: TODO
 * @作者: HuangShiming
 * @创建日期: 2018/1/4 0004
 */
public class TestListener implements MessageListener {

    Logger log = Logger.getLogger("TestListener");

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
