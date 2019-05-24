package com.xuxianda.lessson01;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Xianda Xu on 2018/7/11.
 */
public class ExchangerTest {

    public static void main(String[] args) {
        final Exchanger exchanger = new Exchanger();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    String data1 = "test1";
                    System.out.println("线程"+Thread.currentThread().getName()+"正在把数据"+data1+"交换出去");
                    Thread.sleep((long)(Math.random()*10000));
                    String data2 = (String)exchanger.exchange(data1);
                    System.out.println("线程"+Thread.currentThread().getName()+"交换的数据为"+data2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    String data2 = "test2";
                    System.out.println("线程"+Thread.currentThread().getName()+"正在把数据"+data2+"交换出去");
                    Thread.sleep((long)(Math.random()*10000));
                    String data1 = (String)exchanger.exchange(data2);
                    System.out.println("线程"+Thread.currentThread().getName()+"交换的数据为"+data1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
