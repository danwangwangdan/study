package com.study.thread.start;

public class StartThread1 {

    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
}

class MyThread extends Thread {

    private int ticket = 5;

    public void run() {

        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":ticket = " + ticket--);
            }
        }

    }
}

