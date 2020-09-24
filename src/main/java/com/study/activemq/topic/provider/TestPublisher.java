package com.study.activemq.topic.provider;

import com.common.model.Information;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import java.util.logging.Logger;

public class TestPublisher {

    Logger log = Logger.getLogger("TestPublisher");
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
