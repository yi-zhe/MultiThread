package com.mashibing.juc.c_020;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreFairPrint {
    private Semaphore lock = new Semaphore(1, true);
    private String[] digits = {"1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7"};
    private String[] chars = {"a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g"};

    private Runnable r1 = () -> {
        for (String digit : digits) {
            try {
                lock.acquire();
                System.out.print(digit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        }
    };
    private Runnable r2 = () -> {
        for (String c : chars) {
            try {
                lock.acquire();
                System.out.print(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        }
    };

    public static void main(String[] args) {
        SemaphoreFairPrint rl = new SemaphoreFairPrint();
        Thread th1 = new Thread(rl.r1);
        Thread th2 = new Thread(rl.r2);
        th1.start();
        th2.start();
    }
}
