package com.devloper.lloydfinch.neteasedemo.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: FragmentPagerAdapter
 * Author: lloydfinch
 * Function: 适配器
 * Date: 2019-08-24 17:59
 * Modify: lloydfinch 2019-08-24 17:59
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
