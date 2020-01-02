package com.devloper.lloydfinch.neteasedemo.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * 自定义drawable
 */
class CircleDrawable : Drawable() {
    private var mPaint: Paint = Paint()

    init {
        mPaint.setARGB(255, 255, 0, 0)
    }


    override fun draw(canvas: Canvas) {

        //draw circle

        val width = bounds.width()
        val height = bounds.height()
        val radius: Float = (Math.min(width, height) shr 1).toFloat()
        canvas.drawCircle(radius, radius, radius, mPaint)
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }
}