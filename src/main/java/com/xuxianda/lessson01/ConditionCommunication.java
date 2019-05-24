package com.xuxianda.lessson01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Xianda Xu on 2018/07/06 17:29.
 */
public class ConditionCommunication {

    public static void main(String[] args) {

        final Business Business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    Business.sub(i);
                }
            }
        }).start();
        for (int i = 0; i < 50; i++) {
            Business.main(i);
        }

    }

    static class Business {

        boolean shouldSub = true;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void sub(int i) {
            lock.lock();
            try {
                while (!shouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("sub thread " + j + ", loop" + i);
                }
                shouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (shouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("main thread " + j + ", loop" + i);
                }
                shouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

    }

}

