package com.mashibing.juc.c_020;

import java.util.ArrayList;
import java.util.List;

public class TestSemaphore<T> {
    private List<T> lists = new ArrayList<>();

    public void add(T t) {
        lists.add(t);
    }

    public int size() {
        return lists.size();
    }

    private static Thread t1, t2;

    public static void main(String[] args) {
        TestSemaphore<Integer> container = new TestSemaphore<>();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    t2.start();
                    try {
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                container.add(i);
                System.out.println(i);
            }
        });
        t2 = new Thread(() -> {
            System.out.println("end");
        });
        t1.start();
    }
}
