package com.xuxianda.lessson01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Xianda Xu on 2018/7/8.
 */
public class LockTest {
    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    void init(){
        TraditionalThreadSynchronized.Outputer outputer = new TraditionalThreadSynchronized.Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    outputer.output1("12345");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    outputer.output1("67890");
                }
            }
        }).start();
    }

    static class Outputer{
        Lock lock = new ReentrantLock();
        public void output1(String name){
            lock.lock();
            try {
                for (int i = 0 ; i <name.length() ;i ++) {
                    System.out.print(name.charAt(i));
                }
            } catch (Exception e) {
                lock.unlock();
            }
            System.out.println();
            lock.unlock();
        }

        public synchronized void output2(String name){
            for (int i = 0 ; i <name.length() ;i ++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public synchronized static void output3(String name){
            for (int i = 0 ; i <name.length() ;i ++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

    }
}
