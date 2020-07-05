package com.mashibing.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class T07_ForkJoinPool {
    static final int MAX_NUM = 50000;
    static long[] numbers = new long[10000000];

    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 1;
        }

        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0, numbers.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);
    }

//    static class AddTask extends RecursiveTask<Long> {
//
//        private static final long serialVersionUID = 1L;
//        int start, end;
//
//        AddTask(int s, int e) {
//            start = s;
//            end = e;
//        }
//
//        @Override
//        protected Long compute() {
//
//            if (end - start <= MAX_NUM) {
//                long sum = 0L;
//                for (int i = start; i < end; i++) sum += numbers[i];
//                return sum;
//            }
//
//            int middle = start + (end - start) / 2;
//
//            AddTask subTask1 = new AddTask(start, middle);
//            AddTask subTask2 = new AddTask(middle, end);
//            subTask1.fork();
//            subTask2.fork();
//
//            return subTask1.join() + subTask2.join();
//        }
//
//    }

    static class AddTask extends RecursiveTask<Long> {

        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long result = 0;
                for (int i = start; i < end; i++) {
                    result += numbers[i];
                }
                return result;
            } else {
                int middle = start + (end - start) / 2;
                AddTask sub1 = new AddTask(start, middle);
                AddTask sub2 = new AddTask(middle, end);
                sub1.fork();
                sub2.fork();
                return sub1.join() + sub2.join();
            }
        }
    }
}
