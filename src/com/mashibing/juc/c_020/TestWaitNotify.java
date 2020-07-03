package com.mashibing.juc.c_020;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TestWaitNotify<T> {
    private List<T> lists = new ArrayList<>();

    public void add(T t) {
        lists.add(t);
    }

    public int size() {
        return lists.size();
    }

    private static Thread t1, t2;

    public static void main(String[] args) {
        Object lock = new Object();
        TestWaitNotify<Integer> container = new TestWaitNotify<>();
        t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    container.add(i);
                    System.out.println(i);
                    if (container.size() == 5) {
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.notify();
                }
            }
        });
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }
}
