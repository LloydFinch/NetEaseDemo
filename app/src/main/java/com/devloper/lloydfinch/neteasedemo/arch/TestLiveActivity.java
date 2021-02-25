package com.devloper.lloydfinch.neteasedemo.arch;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Name: TestLiveActivity
 * Author: xchl_wwq
 * Function: TestLiveActivity
 * Date: 1/8/21 2:04 PM
 * Modify: xchl_wwq 1/8/21 2:04 PM
 */
public class TestLiveActivity extends AppCompatActivity {

    private static final String TAG = "TestLiveActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        Observer<String> observer = s -> Log.d(TAG, "onChanged: " + s);
        MutableLiveData<String> name = new MutableLiveData<>();
        name.observe(this, observer);

        name.setValue("android");
        name.postValue("android");

        name.removeObserver(observer);
        name.removeObservers(this);
    }

    private LifecycleRegistry lifecycle = new LifecycleRegistry(this);
}
