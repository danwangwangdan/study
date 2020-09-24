package com.study.activemq.queue.provider;

import com.common.model.Information;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述: TODO
 * @作者: HuangShiming
 * @创建日期: 2018/1/4 0004
 */
public class ProviderQueue {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider_queue.xml");
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
