package com.devloper.lloydfinch.neteasedemo.anko

import com.devloper.lloydfinch.neteasedemo.DialogActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class DialogActivityUI : AnkoComponent<DialogActivity> {
    override fun createView(ui: AnkoContext<DialogActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("hello").onClick {
                toast("hello")
            }

            themedButton("theme", android.R.style.Theme_Black)
        }
    }
}