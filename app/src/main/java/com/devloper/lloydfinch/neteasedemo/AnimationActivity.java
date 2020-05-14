package com.devloper.lloydfinch.neteasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Android 动画库
 */
public class AnimationActivity extends AppCompatActivity {

    private TextView tvTitle;
    private Button btnLogin;
    private Button btnRemove;
    private LinearLayout llDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        init();
    }

    private void init() {

        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        btnRemove = findViewById(R.id.btn_remove);
        llDynamic = findViewById(R.id.ll_dynamic);

        btnLogin.setOnClickListener(v -> {
            addItem();
        });

        btnRemove.setOnClickListener(v -> {
            if (llDynamic.getChildCount() > 0) {
                llDynamic.removeViewAt(0);
            }
        });

    }

    private void addItem() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(4, 0, 4, 0);
        llDynamic.addView(imageView, layoutParams);
    }
}
