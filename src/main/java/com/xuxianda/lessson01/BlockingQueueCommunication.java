package com.xuxianda.lessson01;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Xianda Xu on 2018/7/15.
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {

        final ConditionCommunication.Business Business = new ConditionCommunication.Business();
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

        ArrayBlockingQueue queue1 = new ArrayBlockingQueue(1);
        ArrayBlockingQueue queue2 = new ArrayBlockingQueue(1);

        {
            try {
                queue2.put(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sub(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 10; j++) {
                System.out.println("sub thread " + j + ", loop" + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 10; j++) {
                System.out.println("main thread " + j + ", loop" + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
