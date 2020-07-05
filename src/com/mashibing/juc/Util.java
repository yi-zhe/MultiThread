package com.mashibing.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Util {
    static Random r = new Random();

    public static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void delay(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
