package com.xuxianda.lessson01;

/**
 * Created by Xianda Xu on 2018/07/06 09:46.
 */
public class TraditionalThread {

    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                    System.out.println("1:"+Thread.currentThread().getName());
                    System.out.println("2:"+this.getName());
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                    System.out.println("1:"+Thread.currentThread().getName());
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                    System.out.println("1:"+Thread.currentThread().getName());
                }
            }
        }){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                    System.out.println("2:"+Thread.currentThread().getName());
                }
            }
        };
        thread3.start();

    }

}
