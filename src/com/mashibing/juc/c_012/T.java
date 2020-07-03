package com.mashibing.juc.c_012;

import java.util.concurrent.TimeUnit;

public class T {

    /*volatile*/ boolean running = true;

    // volatile 可见性 禁止指令重排
    void m() {
        System.out.println("m start");
        while (running) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
