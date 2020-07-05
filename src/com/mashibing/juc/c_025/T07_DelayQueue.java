package com.mashibing.juc.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T07_DelayQueue {
    // 按时间进行任务调度
    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static class MyTask implements Delayed {

        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask task1 = new MyTask("t1", now + 1000);
        MyTask task2 = new MyTask("t2", now + 2000);
        MyTask task3 = new MyTask("t3", now + 1500);
        MyTask task4 = new MyTask("t4", now + 2500);
        MyTask task5 = new MyTask("t5", now + 500);

        tasks.put(task1);
        tasks.put(task2);
        tasks.put(task3);
        tasks.put(task4);
        tasks.put(task5);
//        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
