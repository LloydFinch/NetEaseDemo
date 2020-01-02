package com.devloper.lloydfinch.neteasedemo.picasso;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoDemo {
    public void test(ImageView imageView) {

    }

    /**
     * 最基础的功能
     */
    public void test1(ImageView imageView) {
        String url = "";
        Picasso.get().load(url).into(imageView);
    }
}
