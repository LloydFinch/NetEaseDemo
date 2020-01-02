package com.devloper.lloydfinch.neteasedemo;

import com.devloper.lloydfinch.neteasedemo.RxJava.RxJavaDemo;
import com.devloper.lloydfinch.neteasedemo.okhttp.OkHttpDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
//        testJavaApi();
//        testRXJava();

        testOkHttp();
    }

    public static void testJavaApi() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> System.out.println("time:" + System.currentTimeMillis()), 0, 1000, TimeUnit.MILLISECONDS);
    }


    public static void testRXJava() {
        RxJavaDemo rxJavaDemo = new RxJavaDemo();
        rxJavaDemo.test();
    }


    public static void testOkHttp() {
        OkHttpDemo okHttpDemo = new OkHttpDemo();
        okHttpDemo.test();
    }
}
