package com.meilekuan.zhushou_1514.profit.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;

/**
 * function ： 游戏信息
 * author：Meilekuan
 * date: 2016/1/16 16:59
 */

public class GameInfoFragment extends BaseFragment {

    private TextView tvContent;

    @Override
    protected int getLayout() {
        return R.layout.fragment_game_info;
    }

    @Override
    protected void initViews() {
        tvContent = (TextView) root.findViewById(R.id.profit_info_content);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
}
