package com.study.activemq.topic.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTopic {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer_topic.xml");
    }
}
