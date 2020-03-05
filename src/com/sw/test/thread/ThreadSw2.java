package com.sw.test.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 线程练习2----CountDownLatch（允许一个或者多个线程等待一系列指定操作的完成）
 * @author: shaowei
 * @date: 2020-03-05 15:59
 */
public class ThreadSw2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter waiter = new Waiter(latch);
        Waiter2 waiter2 = new Waiter2(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(waiter2).start();
        new Thread(decrementer).start();
    }
}

class Waiter implements Runnable {
    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}

class Waiter2 implements Runnable {
    CountDownLatch latch = null;

    public Waiter2(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter2 Released");
    }
}

class Decrementer implements Runnable {
    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("CountDown开始减");
        try {
            this.latch.countDown();
            this.latch.countDown();
            this.latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CountDown减为0");
    }

}


