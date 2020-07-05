package com.mashibing.juc.c_025;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T05_LinkedBlockingQueue {
    static LinkedBlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i); // 如果满则阻塞
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        String s = strs.take();// 如果空则阻塞
                        System.out.println(Thread.currentThread().getName() + " taking " + s);
                        if (s == null) break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread-" + i).start();
        }
    }
}
