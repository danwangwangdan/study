package com.study.thread.domain;

import com.study.thread.waitandsleep.WaitSleep;

public class SleepThread implements Runnable {

    private WaitSleep waitNotify;

    public SleepThread(WaitSleep waitNotify) {
        this.waitNotify = waitNotify;
    }

    public void run() {
        waitNotify.mSleep();
    }

}
