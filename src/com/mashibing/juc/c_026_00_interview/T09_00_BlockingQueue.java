package com.mashibing.juc.c_026_00_interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T09_00_BlockingQueue {

    static String[] digits = {"1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7"};
    static String[] chars = {"a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g"};

    public static void main(String[] args) {

        BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

        new Thread(() -> {
            for (String digit : digits) {
                try {
                    q1.put(digit);
                    System.out.println(q2.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (String ch : chars) {
                try {
                    System.out.println(q1.take());
                    q1.put(ch);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
