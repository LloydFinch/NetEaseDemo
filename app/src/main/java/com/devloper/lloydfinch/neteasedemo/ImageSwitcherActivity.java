package com.devloper.lloydfinch.neteasedemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.devloper.lloydfinch.neteasedemo.spanable.BackgroundImageSpan;
import com.devloper.lloydfinch.neteasedemo.spanable.RoundBackgroundColorSpan;

public class ImageSwitcherActivity extends AppCompatActivity {


    private Button btnPre;
    private Button btnNext;
    private ImageSwitcher imageSwitcher;

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        initView();
    }

    private void initView() {
        imageSwitcher = findViewById(R.id.image_switcher);
        imageSwitcher.setFactory(() -> new ImageView(ImageSwitcherActivity.this));

        btnPre = findViewById(R.id.btn_pre);
        btnPre.setOnClickListener(v -> {
            imageSwitcher.setImageResource(R.mipmap.ic_launcher);
        });
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(v -> imageSwitcher.setImageResource(R.mipmap.ic_launcher_round));

        testSpan();
    }

    private void testSpan() {
        tvContent = findViewById(R.id.tv_content);

        //展示的内容
        String string = "   10  Tom    7    singer guest hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello";
        SpannableString spannableString = new SpannableString(string);

        //等级的图标展示
        BackgroundImageSpan levelSpan = new BackgroundImageSpan(Color.BLUE, Color.WHITE, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_live_lv_star));
        spannableString.setSpan(levelSpan, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //名字展示


        //vip等级展示
        BackgroundImageSpan vipSpan = new BackgroundImageSpan(Color.BLUE, Color.WHITE, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_vip_bg));
        spannableString.setSpan(vipSpan, 12, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        RoundBackgroundColorSpan roundBackgroundColorSpan = new RoundBackgroundColorSpan(Color.RED, Color.WHITE, 20);
        spannableString.setSpan(roundBackgroundColorSpan, 20, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        RoundBackgroundColorSpan roundBackgroundColorSpan2 = new RoundBackgroundColorSpan(Color.YELLOW, Color.BLACK, 20);
        spannableString.setSpan(roundBackgroundColorSpan2, 27, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvContent.setText(spannableString);

        //等级前面加3个空格，后面加2个空格 共5个
        //vip前面加3个空格，后面加3个空格 共5个
        //称号不用加
    }
}
