package com.xuxianda.lessson01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Xianda Xu on 2018/07/06 17:29.
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {

        final Business Business = new Business();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    Business.sub2(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    Business.sub3(i);
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            Business.sub1(i);
        }
    }

    static class Business {

        int count = 1;
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        public void sub1(int i) {
            lock.lock();
            try {
                while (count != 1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("sub1 thread " + j + ", loop" + i);
                }
                count++;
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub2(int i) {
            lock.lock();
            try {
                while (count != 2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("sub2 thread " + j + ", loop" + i);
                }
                count++;
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub3(int i) {
            lock.lock();
            try {
                while (count != 3) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("sub3 thread " + j + ", loop" + i);
                }
                count = 1;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }

    }

}

