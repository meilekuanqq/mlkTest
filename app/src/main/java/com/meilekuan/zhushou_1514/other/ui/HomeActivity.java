package com.meilekuan.zhushou_1514.other.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.active.ui.ActiveFragment;
import com.meilekuan.zhushou_1514.exchange.ui.ExchangeFragment;
import com.meilekuan.zhushou_1514.gift.ui.GiftFragment;
import com.meilekuan.zhushou_1514.my.ui.MyFragment;
import com.meilekuan.zhushou_1514.profit.ui.ProfitFragment;

/**
 * function ：主页面
 * author：Meilekuan
 * date: 2016/1/11 16:57
 */

public class HomeActivity extends FragmentActivity {

    private Fragment[] fragments;

    private FrameLayout flContent;

    private RadioGroup radioGroup;

    private View checkButton;

    private int checkIndex;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private RadioGroup.OnCheckedChangeListener checked = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            View radioButton = findViewById(checkedId);
            //放大当前选中的
            zoomBig(radioButton);

            if (radioButton == checkButton) {
                return;
            }
            //缩小上一次选中的
            zoomSmall(checkButton);

            //保存当前选中的radiobutton
            checkButton = radioButton;

            int index = 0;
            switch (checkedId) {
                case R.id.home_profit_rb:
                    index = 0;
                    break;
                case R.id.home_gift_rb:
                    index = 1;
                    break;
                case R.id.home_active_rb:
                    index = 2;
                    break;
                case R.id.home_exchange_rb:
                    index = 3;
                    break;
                case R.id.home_my_rb:
                    index = 4;
                    break;
                default:
                    index = 0;
                    break;
            }
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();

            transaction.show(fragments[index]);
            transaction.hide(fragments[checkIndex]);
            transaction.commit();
            checkIndex = index;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        fragments = new Fragment[]{
                new ProfitFragment(),
                new GiftFragment(),
                new ActiveFragment(),
                new ExchangeFragment(),
                new MyFragment()
        };

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            transaction.add(R.id.home_content_fl, fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragments[0]);
        transaction.commit();

    }

    /**
     * 初始化视图
     */
    private void initView() {
        flContent = (FrameLayout) findViewById(R.id.home_content_fl);

        radioGroup = (RadioGroup) findViewById(R.id.home_rg);
        radioGroup.setOnCheckedChangeListener(checked);
        //从radiogroup中获取第一个radiobutton
        View firstChild = radioGroup.getChildAt(0);
        //默认让第一个radiobutton执行放大动画
        checkButton = firstChild;
        firstChild.performClick();

    }

    /**
     * 执行放大动画
     *
     * @param view 要执行动画的view
     */
    private void zoomBig(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rb_zoom_big);
        view.startAnimation(animation);
    }

    /**
     * 执行缩小动画
     *
     * @param view 要执行动画的view
     */
    private void zoomSmall(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rb_zoom_small);
        view.startAnimation(animation);
    }
}
