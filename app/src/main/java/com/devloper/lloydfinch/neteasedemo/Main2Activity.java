package com.devloper.lloydfinch.neteasedemo;

import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: ");

        /**
         * 定义trace文件
         */
        File file = new File(Environment.getExternalStorageDirectory(), "app111.trace");
        Log.d(TAG, "mkdirs: " + file);
        /**
         * 开始追踪
         */
        Debug.startMethodTracing(file.getAbsolutePath());

        /**
         * 被追踪的方法(这里使用几个耗时方法，可以将这些全部延迟，不放在onCreate里面，或者使用异步的方法)
         */
        init();
        test();

        /**
         * 停止追踪s
         */
        Debug.stopMethodTracing();
    }


    private void init() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        aa();
    }

    private void test() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void aa() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
