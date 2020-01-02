package com.devloper.lloydfinch.neteasedemo.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideDemo {

    public void test(ImageView target) {
        test1(target);
    }


    public void test1(ImageView target) {

        String url = "";
        Glide.with(target).load(url).into(target);
    }
}
