package com.sw.test.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier(一种同步机制能够对处理一些算法的线程实现同步 ，
 *就是一个所有线程必须等待的栅栏 ， 所有 ( 一定数量的)线程等待栅栏CyclicBarrier达成，
 * 所有线程将释放掉继续运行)
 * @author: shaowei
 * @date: 2020-03-05 16:22
 */
public class ThreadSw3 {
    public static void main(String[] args) {
        Runnable barrierAction1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("BarrierAction1 executed");
            }
        };
        Runnable barrierAction2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("BarrierAction2 executed");
            }
        };
        CyclicBarrier barrier1 = new CyclicBarrier(2, barrierAction1);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrierAction2);
        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);
        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);
        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
    }
}

class CyclicBarrierRunnable implements Runnable {
    CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;

    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
            this.barrier1.await();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
            this.barrier2.await();
            System.out.println(Thread.currentThread().getName() + "===Done!");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
