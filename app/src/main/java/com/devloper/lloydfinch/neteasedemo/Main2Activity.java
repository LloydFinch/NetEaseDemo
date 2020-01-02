package com.devloper.lloydfinch.neteasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: ");

        Log.d(TAG, "data");

        Log.d(TAG, "onCreate: data");
    }
}
