package com.mashibing.juc.c_026_00_interview;

public class T06_00_CAS {
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {

        String[] digits = {"1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7"};
        String[] chars = {"a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g"};

        new Thread(() -> {
            for (String digit : digits) {
                while (r != ReadyToRun.T1) {
                }
                System.out.print(digit);
                r = ReadyToRun.T2;
            }
        }).start();

        new Thread(() -> {
            for (String ch : chars) {
                while (r != ReadyToRun.T2) {
                }
                System.out.print(ch);
                r = ReadyToRun.T1;
            }
        }).start();
    }

}
