package com.xuxianda.lessson01;

/**
 * Created by Xianda Xu on 2018/7/8.
 */
public class MultiThreadShareData {

    private static ShareData data = new ShareData();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                data.decrement();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                data.increment();
            }
        }).start();
    }

}

class ShareData{
    int j = 0;

    public synchronized void increment(){
        j++;
        System.out.println(j);
    }

    public synchronized void decrement(){
        j--;
        System.out.println(j);
    }

}