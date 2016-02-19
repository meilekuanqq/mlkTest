package com.meilekuan.zhushou_1514.other.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;


/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/13 14:32
 */

public abstract class BaseActivity extends Activity {

    private ImageView ivLeft,ivRight;
    private TextView tvTitle,tvRight;
    protected LinearLayout llContent;
    private RelativeLayout rlRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ivLeft = (ImageView) findViewById(R.id.title_bar_left_iv);
        ivRight = (ImageView) findViewById(R.id.title_bar_right_iv);
        tvTitle = (TextView) findViewById(R.id.title_bar_title_tv);
        tvRight = (TextView) findViewById(R.id.title_bar_right_tv);
        llContent = (LinearLayout) findViewById(R.id.base_content_ll);

        rlRight = (RelativeLayout) findViewById(R.id.title_bar_right_rl);

        //把子类里面的布局添加到title_bar下面的LinearLayout中
        getLayoutInflater().inflate(getLayout(),llContent);
        setDefaultEvent();
        initViews();

        initEvents();

        initData();
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化视图
     */
    protected abstract void initViews();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void setTitleText(String title){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    protected void setTitleText(int StringId){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(StringId);
    }

    protected void setRightText(String text){
        ivRight.setVisibility(View.VISIBLE);
        tvRight.setText(text);
    }

    protected void setRightText(int StringId){
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(StringId);
    }

    protected void setRightImage(int selectorId){
        ivRight.setVisibility(View.VISIBLE);
//        ivRight.setImageDrawable(getResources().getDrawable(selectorId));
        ivRight.setImageResource(selectorId);
    }

    private void setDefaultEvent(){
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setRightOnClickListener(View.OnClickListener onClickListener){
        rlRight.setOnClickListener(onClickListener);
    }
    protected void showLeftImage(){
       ivLeft.setVisibility(View.VISIBLE);
    }
}
