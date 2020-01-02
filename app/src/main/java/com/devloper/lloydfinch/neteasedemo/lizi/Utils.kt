package com.devloper.lloydfinch.neteasedemo.lizi

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

class Utils {

    companion object {

        private val CANVAS = Canvas()
        fun createBitmapFromView(view: View): Bitmap? {
            view.clearFocus()
            val bitmap: Bitmap? = createBitmapSafely(view.width, view.height, Bitmap.Config.ARGB_8888, 1)
            bitmap?.apply {
                synchronized(CANVAS) {
                    CANVAS.setBitmap(this)
                    view.draw(CANVAS)
                    CANVAS.restore()
                    return this
                }
            }
            return null
        }

        private fun createBitmapSafely(width: Int, height: Int, config: Bitmap.Config, rertyCount: Int): Bitmap? {
            try {
                return Bitmap.createBitmap(width, height, config)
            } catch (e: Exception) {
                e.printStackTrace()
                if (rertyCount > 0) {
                    System.gc()
                    return createBitmapSafely(width, height, config, rertyCount - 1)
                }
            }

            return null
        }
    }
}