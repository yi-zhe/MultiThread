package com.mashibing.juc.c_020;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class TestMyContainer<T> {
    private List<T> lists = new ArrayList<>();

    public void add(T t) {
        lists.add(t);
    }

    public int size() {
        return lists.size();
    }

    private static Thread t1, t2;

    public static void main(String[] args) {
        TestMyContainer<Integer> container = new TestMyContainer<>();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(i);
                System.out.println(i);
                if (container.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });
        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("end");
            LockSupport.unpark(t1);
        });
        t2.start();
        t1.start();
    }
}
