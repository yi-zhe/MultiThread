package com.mashibing.juc.c_026_01_ThreadPool;

import com.mashibing.juc.Util;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T05_00_HelloThreadPool {
    static class Task implements Runnable {

        int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + i);
//            try {
//                System.in.read();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            Util.delay(1);
        }

        @Override
        public String toString() {
            return "Task{i=" + i + "}";
        }

        public static void main(String[] args) {
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                    60, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(4),
                    Executors.defaultThreadFactory(),
// 实际情况，以下四个策略都不用，需要自定义拒绝策略
                    new ThreadPoolExecutor.AbortPolicy());
//                    new ThreadPoolExecutor.DiscardPolicy());
//                    new ThreadPoolExecutor.DiscardOldestPolicy());
//                    new ThreadPoolExecutor.CallerRunsPolicy());

            for (int i = 0; i < 8; i++) {
                tpe.execute(new Task(i));
            }
            tpe.execute(new Task(10));
//            System.out.println(tpe.getQueue());
            tpe.shutdown();
        }
    }
}
