package com.devloper.lloydfinch.neteasedemo.lizi

import android.graphics.Canvas
import android.graphics.Paint

abstract class Particle(var cx: Float, var cy: Float, var color: Int) {

    fun advance(canvas: Canvas, paint: Paint, factor: Float) {

    }

    protected abstract fun draw(canvas: Canvas, paint: Paint)

    protected abstract fun calculate(factor: Float)
}