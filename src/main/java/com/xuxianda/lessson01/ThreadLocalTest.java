package com.xuxianda.lessson01;

import java.util.Random;

/**
 * Created by Xianda Xu on 2018/7/7.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    MyThreadScopeData.getThreadInstance().setAge(data);
                    MyThreadScopeData.getThreadInstance().setName("name"+data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        void get() {
            //System.out.println("A " + Thread.currentThread().getName() + " has put data :" + data);
            System.out.println("A " + Thread.currentThread().getName() + " has put data :"
                    + MyThreadScopeData.getThreadInstance().getName() + "&&" + MyThreadScopeData.getThreadInstance().getAge());
        }
    }

    static class B {
        void get() {
            //System.out.println("B " + Thread.currentThread().getName() + " has put data :" + data);
            System.out.println("B " + Thread.currentThread().getName() + " has put data :"
                    + MyThreadScopeData.getThreadInstance().getName() + "&&" + MyThreadScopeData.getThreadInstance().getAge());
        }
    }

}

class MyThreadScopeData{

    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    public static MyThreadScopeData getThreadInstance(){
        MyThreadScopeData myThreadScopeData = map.get();
        if(myThreadScopeData == null){
            myThreadScopeData = new MyThreadScopeData();
            map.set(myThreadScopeData);
        }
        return myThreadScopeData;
    }

    private MyThreadScopeData(){}

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}