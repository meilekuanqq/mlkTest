package com.meilekuan.zhushou_1514.my.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;

/**
 * function ：我的界面
 * author：Meilekuan
 * date: 2016/1/12 10:39
 */

public class MyFragment extends BaseFragment {

    private RelativeLayout rlUserName;
    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViews() {
        rlUserName = (RelativeLayout) root.findViewById(R.id.myinfo_rl_user_name_rl);

    }

    @Override
    protected void initEvents() {
        rlUserName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getActivity(),MyUesrloginActivity.class);
                getActivity().startActivity(intent);
                return false;
            }
        });
//        rlUserName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),MyUesrloginActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });
    }

    @Override
    protected void initData() {

    }
}
