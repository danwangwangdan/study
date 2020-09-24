package com.study.thread.consumerprovider;

/**
 * 生产者和消费者在同一时间段内共用同一存储空间，生产者向空间里生产数据，而消费者取走数据。
 * 问题：
 * 1. 假设生产者线程刚向数据存储空间添加了数据的名称，还没有加入该信息的内容，程序就切换到了消费者线程，消费者线程将把信息的名称和上一个信息的内容联系在一起；
 * 2. 生产者生产了若干次数据，消费者才开始取数据，或者是，消费者取完一次数据后，还没等生产者放入新的数据，又重复取出了已取过的数据
 * 解决办法：
 * 1. 需要同步synchronized
 * 2. 需要线程间通信，生产者线程放入数据后，通知消费者线程取出数据，消费者线程取出数据后，通知生产者线程生产数据，这里用 wait/notify 机制来实现。
 */


class Info { // 定义信息类
    private String name = "name";//定义name属性，为了与下面set的name属性区别开
    private String content = "content";// 定义content属性，为了与下面set的content属性区别开
    private boolean flag = true;   // 设置线程标志位,初始时先生产

    public synchronized void put(String name, String content) {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setName(name);    // 设置名称
        this.setContent(content);  // 设置内容
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("放入了:" + name + " --> " + content);
        flag = false; // 改变标志位，表示可以取走
        this.notify();
    }

    public synchronized void get() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("取走了：" + this.getName() +
                " --> " + this.getContent());
        flag = true;  // 改变标志位，表示可以生产
        this.notify();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class Producer implements Runnable { // 通过Runnable实现多线程
    private Info info = null;      // 保存Info引用

    public Producer(Info info) {
        this.info = info;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            this.info.put("姓名" + i, "内容" + i);
        }
    }
}

class Consumer implements Runnable {
    private Info info = null;

    public Consumer(Info info) {
        this.info = info;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            this.info.get();
        }
    }
}

public class ConsumerProvider {
    public static void main(String args[]) {
        Info info = new Info(); // 实例化Info对象
        Producer pro = new Producer(info); // 生产者
        Consumer con = new Consumer(info); // 消费者
        new Thread(pro).start();
        //启动了生产者线程后，再启动消费者线程
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(con).start();
    }
}