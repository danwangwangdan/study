package com.study.thread.waitnotify;

import java.util.concurrent.TimeUnit;

public class WaitNotify {

    private static volatile boolean flag = true;
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();

    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            // 加锁
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了当前锁（阻塞当前线程并释放资源，让其他线程运行起来）
                while (flag) {
                    System.out.println(Thread.currentThread() + "flag is true. wait @ " + System.currentTimeMillis());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 当条件满足时，做其他事情
                System.out.println(Thread.currentThread() + "flag is false. do other things @ " + System.currentTimeMillis());
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            // 加锁
            synchronized (lock) {
                // 获取当前锁，然后进行notify，notify不会释放锁，直到当前线程释放锁之后，Wait 线程才能从wait方法中返回
                System.out.println(Thread.currentThread() + "hold lock. notify @ " + System.currentTimeMillis());
                lock.notify(); // 这里注意notify()不会抛出异常，而wait()会抛出异常
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 加上这一段代码的原因是为了证明 当notify() 被调用时，wait线程并没有立即恢复，而是要等到notify线程跑完。
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again. sleep @ " + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


