package com.meilekuan.zhushou_1514.profit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/16 17:28
 */

public class GameTypeAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public GameTypeAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public GameTypeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
