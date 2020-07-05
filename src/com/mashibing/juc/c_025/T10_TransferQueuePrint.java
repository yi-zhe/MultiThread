package com.mashibing.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class T10_TransferQueuePrint {

    public static void main(String[] args) throws InterruptedException {

        String[] digits = {"1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7"};
        String[] chars = {"a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g"};

        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            for (String aChar : chars) {
                try {
                    System.out.print(strs.take());
                    strs.transfer(aChar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (String digit : digits) {
            strs.transfer(digit);
            System.out.print(strs.take());
        }
    }
}
