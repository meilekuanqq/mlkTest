package com.meilekuan.zhushou_1514.my.ui;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.other.ui.BaseActivity;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/16 13:11
 */

public class RegisterActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.activity_user_register);
        showLeftImage();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
}
