package com.mashibing.juc.c_001;

public class T {
    private int count = 10;
    private final Object o = new Object();

    public void m() {
        synchronized (this) { // 想锁谁都可以
//        synchronized (o) { // 想锁谁都可以
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}
