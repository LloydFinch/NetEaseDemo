package com.devloper.lloydfinch.neteasedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.devloper.lloydfinch.neteasedemo.anko.DialogActivityUI
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class DialogActivity : AppCompatActivity() {

    private lateinit var btnToast: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dialog)
//        init()

        //这里自动调用了setContentView()函数
//        testAnko()

        //直接调用组件化玩意
        DialogActivityUI().setContentView(this)
    }

    private fun init() {
        val verticalLayout = verticalLayout {
            val name = editText()
            button("hello") {
                onClick { toast("hello world!") }
            }
        }
    }


    /**
     * anko 基本元素单位
     */
    private fun testAnko() {
        verticalLayout {
            padding = dip(30)
            editText {
                hint = "name"
                textSize = sp(18).toFloat()
            }
            editText {
                hint = "password"
                textSize = sp(18).toFloat()
            }
            button("login") {
                textSize = sp(20).toFloat()
            }.onClick {
                toast("login success")
            }
        }
    }
}
