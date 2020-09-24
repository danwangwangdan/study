package com.study.thread.threadlocal;

public class MyThreadLocal {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());

    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) {
        MyThreadLocal.begin();
        //try {
        //    TimeUnit.SECONDS.sleep(1);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        System.out.println("Cost:" + MyThreadLocal.end() + "mills");
    }

}
