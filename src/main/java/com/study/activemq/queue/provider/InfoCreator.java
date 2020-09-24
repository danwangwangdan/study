package com.study.activemq.queue.provider;

import com.common.model.Information;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @描述: TODO
 * @作者: HuangShiming
 * @创建日期: 2018/1/4 0004
 */
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
