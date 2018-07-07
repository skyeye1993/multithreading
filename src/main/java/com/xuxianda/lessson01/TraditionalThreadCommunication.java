package com.xuxianda.lessson01;

/**
 * Created by Xianda Xu on 2018/07/06 17:29.
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {

        final Business Business = new Business();
        new Thread(new Runnable() {
            @Override
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

}

class Business{

    boolean shouldSub = true;

    public synchronized void sub(int i){
        while(!shouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 10; j++) {
            System.out.println("sub thread " + j + ", loop" + i);
        }
        shouldSub = false;
        this.notify();
    }

    public synchronized void main(int i){
        while(shouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 10; j++) {
            System.out.println("main thread " + j + ", loop" + i);
        }
        shouldSub = true;
        this.notify();
    }

}