package com.xuxianda.lessson01;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Xianda Xu on 2018/07/10 14:37.
 */
public class CycleBarrierTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0 ;i < 3 ;i ++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println(Thread.currentThread().getName()+"到达集合点1，当前已有"+(cyclicBarrier.getNumberWaiting()+1));
                        cyclicBarrier.await();

                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println(Thread.currentThread().getName()+"到达集合点2，当前已有"+(cyclicBarrier.getNumberWaiting()+1));
                        cyclicBarrier.await();

                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println(Thread.currentThread().getName()+"到达集合点3，当前已有"+(cyclicBarrier.getNumberWaiting()+1));
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
