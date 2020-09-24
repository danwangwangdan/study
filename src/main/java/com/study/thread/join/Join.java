package com.study.thread.join;

public class Join implements Runnable {

    public static int a = 0;

    public static void main(String[] args) throws Exception {
        Runnable r = new Join();
        Thread t = new Thread(r);
        t.start();
        // join()把t这个线程加入到main线程，这样程序会在t线程执行完毕后，才回到main线程
        t.join();

        System.out.println(a);
    }

    @Override
    public void run() {
        for (int k = 0; k < 5; k++) {
            a = a + 1;
        }
    }
}
