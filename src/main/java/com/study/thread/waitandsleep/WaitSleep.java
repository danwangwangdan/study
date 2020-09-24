package com.study.thread.waitandsleep;

public class WaitSleep {

    /**
     * sleep和wait的区别：
     * 调用sleep() 方法的线程不会释放对象锁，而调用wait() 方法会释放对象锁
     * 补充：什么是释放对象锁？
     * 阻塞当前线程并释放资源，让其他线程运行起来；而sleep只会阻塞当前线程，并不会释放资源，其他线程仍然会处于阻塞状态
     */

    public void mSleep() {
        synchronized (this) {

            try {
                Thread.sleep(3 * 1000);
                this.notifyAll();
                System.out.println(" 唤醒等待 。 结束时间：" + System.currentTimeMillis());
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    public void mWait() {

        synchronized (this) {
            try {
                System.out.println(" 等待开始 。 当前时间：" + System.currentTimeMillis());
                this.wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
