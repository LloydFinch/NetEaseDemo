package com.devloper.lloydfinch.neteasedemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Name: MButton
 * Author: lloydfinch
 * Function: 测试事件分发
 * Date: 2020-01-05 18:02
 * Modify: lloydfinch 2020-01-05 18:02
 */
public class MButton extends android.support.v7.widget.AppCompatButton {
    private static final String TAG = "MButton";

    public MButton(Context context) {
        super(context);
    }

    public MButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + hashCode() + ", " + event.getAction());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.e(TAG, hashCode() + ": ACTION_DOWN");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.e(TAG, hashCode() + ": ACTION_UP");
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            Log.e(TAG, hashCode() + ": ACTION_CANCEL");
        }


        return super.onTouchEvent(event);
    }
}
