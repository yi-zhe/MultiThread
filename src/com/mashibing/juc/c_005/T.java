package com.mashibing.juc.c_005;

public class T implements Runnable {
    private int count = 500000;


    @Override
    public void run() {
        count--;
        System.out.println("count=" + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 500000; i++) {
            new Thread(t).start();
        }
    }
}
