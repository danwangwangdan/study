package com.study.activemq.queue.provider;

import com.common.model.Information;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * @描述: TODO
 * @作者: HuangShiming
 * @创建日期: 2018/1/4 0004
 */
public class TestPublisher {

    /**
     * jms发送器
     */
    private JmsTemplate template;

    private Destination testDest;

    public void sendTestQueueMessage(Information info) {
        try {
            template.send(testDest, new InfoCreator(info));
            System.err.println("发送消息成功" + info.toString());
        } catch (Exception e) {
            System.err.println("发送消息失败" + info.toString());
        }
    }


    public JmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }

    public Destination getTestDest() {
        return testDest;
    }

    public void setTestDest(Destination testDest) {
        this.testDest = testDest;
    }
}
