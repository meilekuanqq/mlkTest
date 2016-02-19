package com.meilekuan.zhushou_1514.exchange.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/14 19:19
 */

public class ExchangePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public ExchangePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
}
