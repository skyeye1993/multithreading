package com.xuxianda.lessson01;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Xianda Xu on 2018/7/8.
 */
public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "name";
            }
        });
        try {
            String s = future.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 0 ;i < 10 ; i++){
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        for (int i = 0 ;i < 10 ; i++){
            try {
                System.out.println(completionService.take().get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
