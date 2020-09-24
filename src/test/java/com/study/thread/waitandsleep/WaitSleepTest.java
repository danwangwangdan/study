package com.study.thread.waitandsleep;

import com.study.thread.domain.SleepThread;
import com.study.thread.domain.WaitThread;
import org.junit.Before;

public class WaitSleepTest {
    public static void main(String[] args) {

        WaitSleep waitSleep = new WaitSleep();
        Thread sleepThread = new Thread(new SleepThread(waitSleep));
        Thread waitThread = new Thread(new WaitThread(waitSleep));
        waitThread.start();
        sleepThread.start();
    }

    @Before
    public void setUp() throws Exception {

    }
}