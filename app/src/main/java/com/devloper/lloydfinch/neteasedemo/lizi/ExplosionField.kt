package com.devloper.lloydfinch.neteasedemo.lizi

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlin.random.Random

class ExplosionField(context: Context) : View(context) {

    private fun attach2Activity(activity: Activity) {
        val rootView: ViewGroup = activity.findViewById(Window.ID_ANDROID_CONTENT)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        rootView.addView(this, params)
    }

    fun addListener(view: View) {
        if (view is ViewGroup) {
            val count = view.childCount
            for (i in 0..count) {
                addListener(view)
            }
        } else {
            view.isClickable = true
            view.setOnClickListener {
                //开始执行粒子动画
                explode(this)
            }
        }
    }

    /**
     * 分裂
     */
    fun explode(view: View) {

        //获取view相对整个屏幕的位置
        val rect = Rect()
        view.getGlobalVisibleRect(rect)

        //标题栏高度
        val currentTop = (parent as ViewGroup).top

        //获取StatusBar的位置
        val frame = Rect()
        (context as Activity).window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top

        //去掉状态栏和标题栏的高
        rect.offset(0, -currentTop - statusBarHeight)
        if (rect.width() == 0 || rect.height() == 0) {
            return
        }

        //开始震动
        val animator = ValueAnimator.ofFloat(0f, 1.0f).setDuration(150)
        animator.addUpdateListener { animation ->
            view.translationX = view.width * 0.05f * (Random(1).nextFloat() - 0.5f)
            view.translationY = view.height * 0.05f * (Random(1).nextFloat() - 0.5f)
        }
        animator.addUpdateListener {
            view.translationX = 0f
            view.translationY = 0f
        }
        animator.start()
        explode(view, rect)
    }

    fun explode(view: View, rect: Rect) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}