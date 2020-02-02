package com.devloper.lloydfinch.neteasedemo;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

public class ToastTestActivity extends AppCompatActivity {

    private static final String TAG = "ToastTestActivity";

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_test);


        // testToast();
        testToastShowTwice();
        // testViewStubSourceCode();
    }

    /**
     * 测试吐司跨线程连点
     */
    private void testToast() {
        findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + Thread.currentThread().getName());
                toast.setText("world"); //这个不走Toast的Handler，也就是在UI里面跑的
                toast.show(); //这个走的是Toast的Handler，Toast在哪个线程创建就是哪个线程，而不是在哪里调用
            }
        });

        new Thread() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + Thread.currentThread().getName());

                Looper.prepare();
                toast = Toast.makeText(ToastTestActivity.this, "hello", Toast.LENGTH_LONG);
                toast.show();
                Looper.loop();
            }
        }.start();
    }

    /**
     * 测试同一个吐司显示两次
     */
    private Toast mToast;

    private void testToastShowTwice() {
        // new Thread() {
        //     @Override
        //     public void run() {
        //         Looper.prepare();
        //         mToast = Toast.makeText(ToastTestActivity.this, "hello", Toast.LENGTH_LONG);
        //         mToast.setText("test twice");
        //         Looper.loop();
        //     }
        // }.start();

        mToast = Toast.makeText(ToastTestActivity.this, "hello", Toast.LENGTH_LONG);
        mToast.setText("test twice");
        findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.show();
                // Toast.makeText(ToastTestActivity.this, "new toast", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * ViewStub源码追踪
     */
    private void testViewStubSourceCode() {
        ViewStub viewStub = findViewById(R.id.view_stub);
        findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * inflate只能调用一次，因为inflate后会把自己移除，
                 * 就没有parent了
                 */
                if (viewStub.getParent() != null) {
                    viewStub.inflate();
                }
            }
        });
    }
}
