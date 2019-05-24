package com.xuxianda.lessson01;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Xianda Xu on 2018/7/7.
 */
public class ThreadScopeShareDate {

    private static int data = 0;
    private static Map<Thread, Integer> threadMap = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    threadMap.put(Thread.currentThread(),data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        void get() {
            //System.out.println("A " + Thread.currentThread().getName() + " has put data :" + data);
            System.out.println("A " + Thread.currentThread().getName() + " has put data :" + threadMap.get(Thread.currentThread()));
        }
    }

    static class B {
        void get() {
            //System.out.println("B " + Thread.currentThread().getName() + " has put data :" + data);
            System.out.println("B " + Thread.currentThread().getName() + " has put data :" + threadMap.get(Thread.currentThread()));
        }
    }

}
