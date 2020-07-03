package com.mashibing.juc.c_016;

import java.util.concurrent.TimeUnit;

/**
 * 锁粗化和锁细化
 *
 * 对象最好用final修饰
 */
public class FineCoarseLock {

    int count = 0;

    void m1() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 只在需要加锁的地方加synchronized 锁细化
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 如果有大量小锁，不如换个大锁
    void m2() {

    }
}
