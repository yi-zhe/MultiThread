package com.mashibing.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T04_ReentrantLock4 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lockInterruptibly();
            System.out.println("m2...start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("m2...end");
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("Interrupted");
        } finally {
            // 被打断后，这个代码有问题
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T04_ReentrantLock4 rl = new T04_ReentrantLock4();
        Thread t1 = new Thread(rl::m1);
        t1.start();

        Thread t2 = new Thread(rl::m2);
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
