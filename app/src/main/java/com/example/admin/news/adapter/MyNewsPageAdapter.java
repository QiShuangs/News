package com.example.admin.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.admin.news.Utils.Constant;

import java.util.List;

/**
 * Created by admin on 2016-12-14.
 */

public class MyNewsPageAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    public MyNewsPageAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.TITLES[position];
    }
}
