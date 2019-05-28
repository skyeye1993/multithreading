package com.xuxianda.lesson02;

import java.util.concurrent.FutureTask;

/**
 * @Author: xuxianda
 * @Date: 2019/5/28 15:16
 * @Version 1.0
 * 创建线程的方式之三：实现callable接口
 */
public class TestCallable {

    public static void main(String[] args){
        FutureTask<Integer> futureTask = new FutureTask<Integer>(
                ()->{
                    Thread.sleep(1000);
                    int sum = 0;
                    for(int i=0;i<1000000000;i++){
                        sum+=i;
                    }
                    return sum;
                }
        );
        new Thread(futureTask).start();
        System.out.println("**************************************");
        //接收运算后的结果
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
            System.out.println("----------------------------------------");
        } catch (Exception e){

        }

    }

}