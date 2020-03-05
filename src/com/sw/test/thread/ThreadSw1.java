package com.sw.test.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @description: 线程练习1---ScheduledExecutorService
 * @author: shaowei
 * @date: 2020-03-05 15:49
 */
public class ThreadSw1 {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Future<String> future = executorService.schedule(() -> {
            return "Hello world";
        }, 1, TimeUnit.SECONDS);

        // 该操作在提供的初始延迟之后首先被调用，随后是给定的时间段，直到服务实例关闭。
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("=======a");
        }, 1, 2, TimeUnit.SECONDS);

        // 改方法与所述执行的一个的终止和的调用之间的给定的延迟创建并执行所提供的初始延迟后首先调用的周期性动作，并重复地下一个。
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("=======");
        }, 1, 2, TimeUnit.SECONDS);

        executorService.shutdown();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("asss");
            }
        },3000,5000);

    }

}
