package com.xuxianda.lessson01;

/**
 * Created by Xianda Xu on 2018/07/06 17:29.
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (TraditionalThreadCommunication.class) {
                    for (int i = 0; i < 50; i++) {
                        for (int j = 0; j < 10; j++) {
                            System.out.println("sub thread " + j + ", loop" + i);
                        }
                    }
                }
            }
        }).start();
        for (int j = 0; j < 10; j++) {
            synchronized (TraditionalThreadCommunication.class) {
                System.out.println("main thread " + j);
            }
        }
    }

}
