package com.mashibing.juc.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T08_SynchronousQueue {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> strs = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); // 阻塞等待消费者 容量为0
        System.out.println(strs.size());
    }

}
