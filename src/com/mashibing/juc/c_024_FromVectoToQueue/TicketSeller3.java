package com.mashibing.juc.c_024_FromVectoToQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
    final static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("编号:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        System.out.println("销售:" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
