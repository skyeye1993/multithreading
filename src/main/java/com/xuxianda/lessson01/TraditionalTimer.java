package com.xuxianda.lessson01;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Xianda Xu on 2018/07/06 14:16.
 */
public class TraditionalTimer {

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing");
            }

        },10000,3000);//10秒之后打印bombing
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new Date().getSeconds());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
