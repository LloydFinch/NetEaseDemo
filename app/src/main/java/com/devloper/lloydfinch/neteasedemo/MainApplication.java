package com.devloper.lloydfinch.neteasedemo;

import android.app.Application;

public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
