package com.mashibing.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T08_TestPhase {

    static Random r = new Random();
    static Phaser phaser = new MarriagePhaser();

    static void sleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("person" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("到齐" + registeredParties);
                    return false;
                case 1:
                    System.out.println("吃完" + registeredParties);
                    return false;
                case 2:
                    System.out.println("离开" + registeredParties);
                    return false;
                case 3:
                    System.out.println("happy" + registeredParties);
                    return true;
                default:
                    return false;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            sleep(r.nextInt(1000));
            System.out.println(name + " arrive");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            sleep(r.nextInt(1000));
            System.out.println(name + " eat");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            sleep(r.nextInt(1000));
            System.out.println(name + " leave");
            phaser.arriveAndAwaitAdvance();
        }

        public void hug() {
            if ("新郎".equals(name) || "新娘".equals(name)) {
                sleep(r.nextInt(1000));
                System.out.println("go to happy");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
