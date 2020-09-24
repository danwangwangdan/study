package com.study.activemq.topic.provider;

import com.common.model.Information;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderTopic {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider_topic.xml");
        TestPublisher testPublisher = (TestPublisher) context.getBean("testPublisher");
        for (int i = 0; i < 10; i++) {
            Information information = new Information();
            information.setType("type" + i);
            information.setCategory("category" + i);
            testPublisher.sendTestQueueMessage(information);
        }
        context.close();
    }

}
