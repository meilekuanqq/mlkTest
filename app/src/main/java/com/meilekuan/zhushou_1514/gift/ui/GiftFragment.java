package com.meilekuan.zhushou_1514.gift.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.gift.adapter.GiftPagerAdapter;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * function ：礼包界面
 * author：Meilekuan
 * date: 2016/1/12 10:39
 */
public class GiftFragment extends BaseFragment {

    private ViewPager vpContent;
    private GiftPagerAdapter pagerAdapter;

    private TextView tvMobile, tvWeb;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int index = 0;
            //如果点击的是手游礼包
            if (id == R.id.gift_type_moble) {
//                vpContent.setCurrentItem(0);
//                tvMobile.setSelected(true);
//                tvWeb.setSelected(false);
                index = 0;
            }
            //如果点击的是页游礼包
            else if (id == R.id.gift_type_web) {
//                vpContent.setCurrentItem(1);
//                tvMobile.setSelected(false);
//                tvWeb.setSelected(true);
                index = 1;
            }
            vpContent.setCurrentItem(index);
            boolean isCheckMobile = index == 0 ? true : false;

            tvMobile.setSelected(isCheckMobile);
            tvWeb.setSelected(!isCheckMobile);
        }
    };

    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            boolean isCheckMobile = position == 0 ? true : false;
            tvMobile.setSelected(isCheckMobile);
            tvWeb.setSelected(!isCheckMobile);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected int getLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initViews() {
        vpContent = (ViewPager) root.findViewById(R.id.gift_type_vp);
        tvMobile = (TextView) root.findViewById(R.id.gift_type_moble);
        tvWeb = (TextView) root.findViewById(R.id.gift_type_web);
        //默认手游礼包被选中
        tvMobile.setSelected(true);
        vpContent.setOnPageChangeListener(changeListener);
    }

    @Override
    protected void initEvents() {
        tvMobile.setOnClickListener(onClickListener);
        tvWeb.setOnClickListener(onClickListener);
    }

    @Override
    protected void initData() {
        GiftListFragment mobileFragment = new GiftListFragment(1);
        GiftListFragment webFragment = new GiftListFragment(2);
        List<Fragment> list = new ArrayList<>();
        list.add(mobileFragment);
        list.add(webFragment);
        pagerAdapter = new GiftPagerAdapter(getFragmentManager(), list);

        vpContent.setAdapter(pagerAdapter);
    }

}
