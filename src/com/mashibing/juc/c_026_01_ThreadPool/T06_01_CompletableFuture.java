package com.mashibing.juc.c_026_01_ThreadPool;

import com.mashibing.juc.Util;

import java.util.concurrent.CompletableFuture;

public class T06_01_CompletableFuture {
    public static void main(String[] args) {
        long start, end;

        start = System.currentTimeMillis();
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> {
            Util.delay();
            return 1.00;
        });
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> {
            Util.delay();
            return 1.02;
        });
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> {
            Util.delay();
            return 0.98;
        });

        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
