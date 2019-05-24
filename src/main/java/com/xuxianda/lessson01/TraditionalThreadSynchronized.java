package com.xuxianda.lessson01;

/**
 * Created by Xianda Xu on 2018/07/06 15:52.
 */
public class TraditionalThreadSynchronized {

    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
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

        public void output1(String name){
            synchronized (this){
                for (int i = 0 ; i <name.length() ;i ++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
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
