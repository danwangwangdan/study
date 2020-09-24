package com.study.thread.daemon;

import java.util.concurrent.TimeUnit;

class ADaemon implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("ADaemon Starting");
            //在打印完线程启动标志之后再打印一下东西
            for (int i = 0; i < 5; i++) {
                System.out.println("ADaemon print " + i);
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("Always run?");
        }
    }
}

public class DaemonFinally {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ADaemon());
        thread.setDaemon(true);//设置为后台线程
        thread.start();

        //让main方法停1秒之后再结束
        Thread.sleep(4000);
    }
}