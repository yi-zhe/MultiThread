package com.mashibing.juc.c_025;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        // 适用于写特别少，但读特别多的情况
        List<String> lists = new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            Runnable task = () -> {
                for (int j = 0; j < 1000; j++) {
                    lists.add("a" + r.nextInt(10000));
                }
            };
            threads[i] = new Thread(task);
        }
        runAndComputeTime(threads);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(Thread::start);
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
