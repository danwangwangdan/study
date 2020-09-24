package com.study.thread.start;

public class StartThread2 {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        new Thread(myThread2).start();
        new Thread(myThread2).start();
        new Thread(myThread2).start();
        // new Thread(myThread2).run();
    }

}

class MyThread2 implements Runnable {

    private int ticket = 5;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":ticket = " + ticket--);
            }
        }

    }
}
