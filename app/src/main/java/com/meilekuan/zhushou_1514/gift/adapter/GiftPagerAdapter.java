package com.meilekuan.zhushou_1514.gift.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * function ：礼包页面下面的viewpager 适配器
 *
 * author：Meilekuan
 *
 * date: 2016/1/12 18:52
 */

public class GiftPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public GiftPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
