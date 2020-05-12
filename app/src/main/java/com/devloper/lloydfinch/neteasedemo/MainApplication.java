package com.devloper.lloydfinch.neteasedemo;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MainApplication extends Application {

    private static final String TAG = "MainApplication";

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        /**
         * 初始化内存泄漏检测器
         */
        {
            if (!LeakCanary.isInAnalyzerProcess(this)) {
                RefWatcher refWatcher = LeakCanary.install(this);
                Log.d(TAG, "onCreate: refWatcher = " + refWatcher);
            }
        }
    }
}
