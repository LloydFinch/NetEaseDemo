package com.devloper.lloydfinch.neteasedemo.anko.tools;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.devloper.lloydfinch.neteasedemo.MainApplication;

/**
 * Name: ScreenUtils
 * Author: lloydfinch
 * Function: 进行屏幕像素密度等单位的转换
 * Date: 2019-09-20 09:47
 * Modify: lloydfinch 2019-09-20 09:47
 */
public class ScreenUtils {

    public static int dpToPx(int dp) {

        DisplayMetrics displayMetrics = MainApplication.getInstance().getResources().getDisplayMetrics();
        int px = TypedValue.complexToDimensionPixelSize(dp, displayMetrics);

        return px;
    }
}
