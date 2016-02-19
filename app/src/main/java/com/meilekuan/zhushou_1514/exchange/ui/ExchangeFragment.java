package com.meilekuan.zhushou_1514.exchange.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.exchange.adapter.ExchangePagerAdapter;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * function ：兑换界面
 * author：Meilekuan
 * date: 2016/1/12 10:39
 */

public class ExchangeFragment extends BaseFragment {

    private ViewPager vpContent;
    private TextView tvNew,tvHot;
    private ExchangePagerAdapter pagerAdapter;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int index = 0;
           if (id == R.id.exchange_type_new){
               index = 0;
           }else if (id == R.id.exchange_type_hot){
               index = 1;
           }
            vpContent.setCurrentItem(index);
            boolean isCheckMobile = index == 0 ? true : false;

            tvNew.setSelected(isCheckMobile);
            tvHot.setSelected(!isCheckMobile);
        }
    };


    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            boolean isCheckMobile = position == 0 ? true : false;
            tvNew.setSelected(isCheckMobile);
            tvHot.setSelected(!isCheckMobile);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected int getLayout() {
        return R.layout.fragment_exchange;
    }

    @Override
    protected void initViews() {
        vpContent = (ViewPager) root.findViewById(R.id.exchange_type_vp);
        tvNew = (TextView) root.findViewById(R.id.exchange_type_new);
        tvHot = (TextView) root.findViewById(R.id.exchange_type_hot);
        //默认手游礼包被选中
        tvNew.setSelected(true);
        vpContent.setOnPageChangeListener(listener);
    }

    @Override
    protected void initEvents() {
            tvNew.setOnClickListener(onClickListener);
            tvHot.setOnClickListener(onClickListener);
    }

    @Override
    protected void initData() {

        ExchangeListFragment newFragment = new ExchangeListFragment(1);
        ExchangeListFragment hotFragment = new ExchangeListFragment(2);
        List<Fragment> list = new ArrayList<>();
        list.add(newFragment);
        list.add(hotFragment);
        pagerAdapter = new ExchangePagerAdapter(getFragmentManager(), list);

        vpContent.setAdapter(pagerAdapter);
    }
}
