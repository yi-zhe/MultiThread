package com.mashibing.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = () -> "Hello Callable";

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c);
        System.out.println(future.get());
        service.shutdown();
        System.out.println(service.isShutdown());
    }
}
