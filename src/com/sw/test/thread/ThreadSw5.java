package com.sw.test.thread;

import java.util.concurrent.*;

/**
 * @description: Semaphore(控制并发线程数量)
 * @author: shaowei
 * @date: 2020-03-05 16:34
 */
public class ThreadSw5 extends Thread {

    private Semaphore position;
    private int id;
    private CountDownLatch count;

    public ThreadSw5(int i, Semaphore s, CountDownLatch count) {
        this.id = i;
        this.position = s;
        this.count = count;
    }

    public static void main(String[] args) {
        ExecutorService list = Executors.newCachedThreadPool();
        Semaphore position = new Semaphore(2);//只有两个厕所
        CountDownLatch count = new CountDownLatch(10);
        //有十个人
        for (int i = 0; i < 10; i++) {
            list.submit(new ThreadSw5(i + 1, position, count));
        }
        list.shutdown();
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // acquireUninterruptibly 使等待进入acquire()方法的线程，不允许被中断
        position.acquireUninterruptibly(2);
        System.out.println("使用完毕，需要清扫了");
        position.release(2);
    }

    @Override
    public void run() {
        try {
            //有没有空厕所
            // 返回当前可用的许可数量
            if (position.availablePermits() > 0) {
                System.out.println("顾客[" + this.id + "]进入厕所，有空位");
            } else {
                System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
            }
            //获取到空厕所了
            //  semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
            position.acquire();
            System.out.println("顾客[" + this.id + "]获得坑位");
            //使用中...
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println("顾客[" + this.id + "]使用完毕");
            //厕所使用完之后释放
            position.release();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            count.countDown();
        }

    }

//    Semaphore中的acquire()方法意思是使用一个许可，是一个减法操作。
//    Semaphore中的release()方法意思是增加一个许可，增加同时间允许通过的线程数，Semaphore允许通过的线程数是可以改变的。
//    Semaphore的availablePermits()返回当前允许通过的线程数。
//    semaphore.acquireUninterruptibly();在等待release()的时候不允许被中断。如果使用这段代码，在当前代码到执行release()方法之间，不允许中断。
//    semaphore.drainPermits()清空当前允许通过的线程数，并且返回当前允许的数目。
//    semaphore.hasQueuedThreads()是否还有线程在等待。
//    semaphore.getQueueLength()获取还在等待的线程数。
//    tryAcquire()尝试获取许可。
//    tryAcquire(3)尝试获取3个许可。
//    tryAcquire(3,TimeUnit.SECONDS)在3内获取1个许可，获取不到返回false。
//    tryAcquire(3,3,TimeUnit.SECONDS)在3秒内获取3个许可，获取不到返回false。
//    Semaphore(1,true)构造函数如果是true为公平锁获取，如果是flase则为非公平锁。
}
