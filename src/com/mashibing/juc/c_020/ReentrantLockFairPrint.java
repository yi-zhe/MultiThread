package com.mashibing.juc.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFairPrint {
    private Lock lock = new ReentrantLock(true);
    private String[] digits = {"1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7"};
    private String[] chars = {"a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g"};

    private Runnable r1 = () -> {
        for (String digit : digits) {
            lock.lock();
            try {
                System.out.print(digit);
            } finally {
                lock.unlock();
            }
        }
    };
    private Runnable r2 = () -> {
        for (String c : chars) {
            lock.lock();
            try {
                System.out.print(c);
            } finally {
                lock.unlock();
            }
        }
    };

    public static void main(String[] args) {
        ReentrantLockFairPrint rl = new ReentrantLockFairPrint();
        Thread th1 = new Thread(rl.r1);
        Thread th2 = new Thread(rl.r2);
        th1.start();
        th2.start();
    }
}
