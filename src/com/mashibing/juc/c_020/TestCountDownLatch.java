package com.mashibing.juc.c_020;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch<T> {
    private List<T> lists = new ArrayList<>();

    public void add(T t) {
        lists.add(t);
    }

    public int size() {
        return lists.size();
    }

    private static Thread t1, t2;

    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        TestCountDownLatch<Integer> container = new TestCountDownLatch<>();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(i);
                System.out.println(i);
                if (container.size() == 5) {
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2 = new Thread(() -> {
            if (container.size() != 5) {
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("end");
            latch2.countDown();
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
