package com.study.activemq.queue.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述: TODO
 * @作者: HuangShiming
 * @创建日期: 2018/1/4 0004
 */
public class ConsumerQueue {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer_queue.xml");
    }
}
