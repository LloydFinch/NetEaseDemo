package com.devloper.lloydfinch.neteasedemo.spanable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.style.ReplacementSpan;

import com.devloper.lloydfinch.neteasedemo.anko.tools.ScreenUtils;

/**
 * Name: RoundBackgroundColorSpan
 * Author: lloydfinch
 * Function: 这里实现文字背景图片效果
 * Date: 2019-09-17 11:43
 * Modify: lloydfinch 2019-09-17 11:43
 */
public class BackgroundImageSpan extends ReplacementSpan {
    //背景颜色
    private int bgColor;
    //文字颜色
    private int textColor;
    //半径(圆角) px
    private Bitmap bitmap;

    public BackgroundImageSpan(int bgColor, int textColor, Bitmap bitmap) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.bitmap = bitmap;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return ((int) paint.measureText(text, start, end) + 60);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int color1 = paint.getColor();
        paint.setColor(this.bgColor);
        //这里需要计算一下图片宽高和文字宽高，然后使用做差计算中心位置
        //+40计算的是偏移量，否则后面会有截取效果(上下调整大小超出内容区高度会被裁剪，低于内容区高度会被压缩，左右会被跨区域，末尾空格会被裁剪)
        //坐标的正方向跟View坐标的正方向是一致的，超出内容范围会被截取
        RectF dst = new RectF(x, top, x + ((int) paint.measureText(text, start, end)), bottom);
        //这里尝试写死宽高,看一下效果
        int width = ScreenUtils.dpToPx(40);
        int height = ScreenUtils.dpToPx(20);
//        RectF dst = new RectF(x, top, x + width, top + height);
        canvas.drawBitmap(bitmap, null, dst, paint);
        paint.setColor(this.textColor);
        canvas.drawText(text, start, end, x + 20, y, paint);
        paint.setColor(color1);
    }
}
