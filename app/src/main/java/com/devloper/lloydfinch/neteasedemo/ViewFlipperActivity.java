package com.devloper.lloydfinch.neteasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        initView();
    }

    private void initView() {
        viewFlipper = findViewById(R.id.vf);
        viewFlipper.startFlipping();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewFlipper.stopFlipping();
        viewFlipper.clearAnimation();
    }
}
