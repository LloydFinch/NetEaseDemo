package com.devloper.lloydfinch.neteasedemo.spanable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.style.ReplacementSpan;

/**
 * Name: RoundBackgroundColorSpan
 * Author: lloydfinch
 * Function: 这里实现文字背景圆角效果
 * Date: 2019-09-17 11:43
 * Modify: lloydfinch 2019-09-17 11:43
 */
public class RoundBackgroundColorSpan extends ReplacementSpan {
    //背景颜色
    private int bgColor;
    //文字颜色
    private int textColor;
    //半径(圆角) px
    private int radius;

    public RoundBackgroundColorSpan(int bgColor, int textColor, int radius) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.radius = radius;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return ((int) paint.measureText(text, start, end) + 60);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int color1 = paint.getColor();
        paint.setColor(this.bgColor);
        canvas.drawRoundRect(new RectF(x, top + 1, x + ((int) paint.measureText(text, start, end) + 40), bottom - 1), radius, radius, paint);
        paint.setColor(this.textColor);
        canvas.drawText(text, start, end, x + 20, y, paint);
        paint.setColor(color1);
    }
}
