package com.xuxianda.lesson02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: xuxianda
 * @Date: 2019/5/28 10:18
 * @Version 1.0
 * 一、i++的原子性问题
 *      int i=10;
 *      i=i++;
 */
public class TestAtomicDemo {

    public static void main(String[] args) throws Exception{
        Runnable runnable = new AtomicDemo();
        for(int i=0;i<20;i++){
            new Thread(runnable).start();
        }
    }

}

class AtomicDemo implements Runnable{

//    private volatile int serialNumber = 0;

    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
    }

    public int getSerialNumber(){
        return serialNumber.getAndIncrement();
    }
}
