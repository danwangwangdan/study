package com.study.thread.domain;

import com.study.thread.waitandsleep.WaitSleep;

public class WaitThread implements Runnable {

    private WaitSleep waitNotify;

    public WaitThread(WaitSleep waitNotify) {
        this.waitNotify = waitNotify;
    }

    public void run() {
        waitNotify.mWait();
    }

}