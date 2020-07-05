package com.mashibing.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class T09_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa"); // 装完等着有人取走才继续
        System.out.println("end");
    }
}
