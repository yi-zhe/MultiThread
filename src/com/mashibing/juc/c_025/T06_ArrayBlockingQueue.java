package com.mashibing.juc.c_025;

import java.util.concurrent.ArrayBlockingQueue;

public class T06_ArrayBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        strs.put("aaa"); // 阻塞
//        strs.add("aaa")   // 满则抛出异常
//        strs.offer("aaa") // 满则返回false
        System.out.println(strs);
    }
}
