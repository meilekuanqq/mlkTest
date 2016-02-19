package com.meilekuan.zhushou_1514.other.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.other.dapter.GuideAdapter;
import com.meilekuan.zhushou_1514.other.utils.JumpManager;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouContans;

import java.util.ArrayList;
import java.util.List;

/**
 * function ：引导页面
 * author：Meilekuan on 2016/1/11 11:32
 * E-mail：kuan_14@126.com
 */

public class GuideActivity extends Activity {
    private ViewPager viewPager;
    private GuideAdapter adapter;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager) findViewById(R.id.guide_content_vp);
        button = (Button) findViewById(R.id.guide_jump_btn);

        List<ImageView> list = new ArrayList<>();
        final int[] bitmaps = new int[]{
                R.drawable.bg_guide_01,
                R.drawable.bg_guide_02,
                R.drawable.bg_guide_03,
                R.drawable.bg_guide_04
        };
        for (int i = 0; i < bitmaps.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(bitmaps[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
        adapter = new GuideAdapter(list);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == bitmaps.length - 1) {
                    //如果显示了最后一页，让button显示出来
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //点击进入主界面，并设置进入的标识
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences指定的SharedPreferences
                SharedPreferences preferences = getSharedPreferences(ZhuShouContans.PERFERECE_FIRST_USED, Context.MODE_PRIVATE);

                //获取一个编辑器
                SharedPreferences.Editor editor = preferences.edit();
                //添加一个字段表示第一次使用过了
                editor.putBoolean(ZhuShouContans.PERFERECE_FLAG_USED, false);
                //添加完字段后，要提交操作
                editor.commit();
                //跳转到主页面
                JumpManager.jumpToHome(GuideActivity.this);
                finish();
            }
        });
    }
}
