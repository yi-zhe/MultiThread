package com.mashibing.juc.c_024_FromVectoToQueue;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketSeller4 {
    final static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("编号:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int count = 0;
                while (true) {
                    String s = tickets.poll();
                    if (s == null) break;
                    count++;
                    System.out.println("销售:" + s);
                }
                System.out.println(Thread.currentThread().getName() + " 卖了 " + count);
            }, "线程" + i).start();
        }
    }
}
