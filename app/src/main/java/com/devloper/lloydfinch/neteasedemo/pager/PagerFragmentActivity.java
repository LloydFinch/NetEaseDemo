package com.devloper.lloydfinch.neteasedemo.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.devloper.lloydfinch.neteasedemo.R;

import java.util.ArrayList;
import java.util.List;

public class PagerFragmentActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_fragment);

        init();
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        List<Fragment> fragments = getList(8);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        viewPager.postDelayed(() -> {
            Log.e("ViewPager", "start switch");
            List<Fragment> fragments2 = getList(4);

            adapter.setFragments(fragments2);
        }, 10000);
    }

    private List<Fragment> getList(int count) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            fragments.add(new MyFragment());
        }

        return fragments;
    }

}
