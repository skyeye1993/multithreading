package com.xuxianda.lessson01;

import java.util.concurrent.Semaphore;

/**
 * Created by Xianda Xu on 2018/07/10 14:20.
 * 信号灯
 */
public class SemphoreTest {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);//同时最多只有3个线程启用

        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(5000);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

}
