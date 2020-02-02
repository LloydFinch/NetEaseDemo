package com.devloper.lloydfinch.neteasedemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Name: MLinearLayout
 * Author: lloydfinch
 * Function: //测试事件分发
 * Date: 2020-01-05 18:00
 * Modify: lloydfinch 2020-01-05 18:00
 */
public class MLinearLayout extends LinearLayout {

    private static final String TAG = "MLinearLayout";

    public MLinearLayout(Context context) {
        super(context);
    }

    public MLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }
}
