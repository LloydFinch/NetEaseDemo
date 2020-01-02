package com.devloper.lloydfinch.neteasedemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.View

/**
 * 自定义View绘制Shape
 */
class ShapeView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    constructor(context: Context) : this(context, null)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //ShapeDrawable
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.paint.color = 0xff74AC23.toInt()
        shapeDrawable.setBounds(10, 10, 300, 60)
        //Drawable的draw方法
        shapeDrawable.draw(canvas)
    }
}