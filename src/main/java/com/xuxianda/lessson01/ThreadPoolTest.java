package com.xuxianda.lessson01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Xianda Xu on 2018/7/8.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);//固定三个线程的线程池
        //ExecutorService threadPool = Executors.newCachedThreadPool();//线程缓存池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单线程池
        for (int i = 0; i < 10; i++){
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0 ; j<10;j++){
                        System.out.println(Thread.currentThread().getName()+" is looping of "+j+" for task "+task);
                    }
                }
            });
        }
        //threadPool.shutdown();
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing");
            }
        },10, TimeUnit.SECONDS);
    }

}
