package com.mashibing.juc.c_025;

import java.util.PriorityQueue;

public class T07_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();
        q.add("z");
        q.add("a");
        q.add("e");
        q.add("d");
        q.add("s");
        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}
