package com.meilekuan.zhushou_1514.profit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.gift.view.CircleImageView;
import com.meilekuan.zhushou_1514.other.ui.BaseActivity;
import com.meilekuan.zhushou_1514.other.utils.ImageLoader;
import com.meilekuan.zhushou_1514.other.utils.LogUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouContans;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;
import com.meilekuan.zhushou_1514.other.widget.SharePopWindow;
import com.meilekuan.zhushou_1514.profit.adapter.GameTypeAdapter;
import com.meilekuan.zhushou_1514.profit.bean.ProfitInfo;
import com.meilekuan.zhushou_1514.profit.util.ProfitHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.ShareSDKR;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * function ：游戏详情
 * author：Meilekuan
 * date: 2016/1/15 19:27
 */

public class ProfitInfoActivity extends FragmentActivity {
    private ImageView ivSnapshot, ivTitleLeft, ivRight;
    private CircleImageView ivIcon;
    private TextView tvName,
            tvVersion, tvSize, tvCount, tvContent, tvScore,
    tvInfo, tvGift, tvComment;


    private ViewPager vpGame;

    private List<Fragment> fragments;
    protected LinearLayout llContent;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int index = 0;
            //如果点击的是手游礼包
            if (id == R.id.profit_type_message) {
                vpGame.setCurrentItem(0);
                tvInfo.setSelected(true);
                tvGift.setSelected(false);
                tvComment.setSelected(false);
            }
            //如果点击的是页游礼包
            else if (id == R.id.profit_type_gift) {
                vpGame.setCurrentItem(1);
                tvInfo.setSelected(false);
                tvGift.setSelected(true);
                tvComment.setSelected(false);
            } else if (id == R.id.profit_type_comment) {
                vpGame.setCurrentItem(2);
                tvInfo.setSelected(false);
                tvGift.setSelected(false);
                tvComment.setSelected(true);
            } else if (id == R.id.game_title_bar_left_iv) {
                finish();
            } else if (id == R.id.game_title_bar_right_iv) {
                //显示分享菜单

                shared();
//                SharePopWindow popWindow = new SharePopWindow(ProfitInfoActivity.this);
////                popWindow.showAsDropDown(ivHeader);
//                popWindow.showAtLocation(llContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

            }
        }
    };
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                tvInfo.setSelected(true);
                tvGift.setSelected(false);
                tvComment.setSelected(false);
            } else if (position == 1) {
                tvInfo.setSelected(false);
                tvGift.setSelected(true);
                tvComment.setSelected(false);
            } else {
                tvInfo.setSelected(false);
                tvGift.setSelected(false);
                tvComment.setSelected(true);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);
        initView();
        initData();
        initFragment();
    }
    private void initFragment() {
        fragments = new ArrayList<>();
        GameInfoFragment gameInfo = new GameInfoFragment();
        GameGiftFragment gameGift = new GameGiftFragment();
        CommentFragment gameComment = new CommentFragment();
        fragments.add(gameInfo);
        fragments.add(gameGift);
        fragments.add(gameComment);

        GameTypeAdapter adapter = new GameTypeAdapter(getSupportFragmentManager(), fragments);
        vpGame.setAdapter(adapter);
        vpGame.setOnPageChangeListener(onPageChangeListener);
    }

    private void initView() {
        tvInfo = (TextView) findViewById(R.id.profit_type_message);
        tvGift = (TextView) findViewById(R.id.profit_type_gift);
        tvComment = (TextView) findViewById(R.id.profit_type_comment);
        tvInfo.setSelected(true);
        tvInfo.setOnClickListener(onClickListener);
        tvGift.setOnClickListener(onClickListener);
        tvComment.setOnClickListener(onClickListener);

        ivTitleLeft = (ImageView) findViewById(R.id.game_title_bar_left_iv);
        ivRight = (ImageView) findViewById(R.id.game_title_bar_right_iv);
        ivTitleLeft.setOnClickListener(onClickListener);
        ivRight.setOnClickListener(onClickListener);


        ivIcon = (CircleImageView) findViewById(R.id.profit_info_icon);
        ivSnapshot = (ImageView) findViewById(R.id.profit_info_snapshot);

        tvName = (TextView) findViewById(R.id.adapter_active_name_tv);
        tvVersion = (TextView) findViewById(R.id.profit_info_version);
        tvSize = (TextView) findViewById(R.id.profit_info_size);
        tvCount = (TextView) findViewById(R.id.profit_info_down);
        tvContent = (TextView) findViewById(R.id.profit_info_content);
        tvScore = (TextView) findViewById(R.id.profit_info_jifen);
        vpGame = (ViewPager) findViewById(R.id.proit_game_vp);

        llContent = (LinearLayout) findViewById(R.id.game_content_ll);
        getLayoutInflater().inflate(R.layout.activity_game_info, llContent);

    }

    protected void initViews() {
//        setTitleText(R.string.profit_title_info);
//        showLeftImage();
//        setRightImage(R.drawable.selector_share);
    }


    private void initData() {
        //获取从上一页传进来的id
        String id = getIntent().getStringExtra("id");

        ProfitHttpUtil.requestProfitInfoList(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                if (result == null) {
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(ZhuShouContans.FLAG_REQUEST_STATE);
                    if (ZhuShouContans.FLAG_REQUEST_SUCCESS.equals(state)) {
                        JSONObject info = jsonObject.getJSONObject("info");
                        ProfitInfo profitInfo = ProfitInfo.objectFromData(info.toString());
                        LogUtil.e("mlk", "giftInfo=" + profitInfo);
                        ImageLoader imageLoader = new ImageLoader(ProfitInfoActivity.this);
//                        //头像
                        imageLoader.DisplayImage(profitInfo.getIcon(), ivIcon);
//                        //背景图
                        imageLoader.DisplayImage(profitInfo.getSnapshot(), ivSnapshot);

                        //游戏名称
                        tvName.setText(profitInfo.getName());

                        //游戏版本
                        String version = getApplicationContext().getString(R.string.profit_info_version, profitInfo.getVersion());
                        tvVersion.setText(version);

                        tvSize.setText(profitInfo.getSize());
                        String count = getApplicationContext().getString(R.string.profit_info_count, profitInfo.getCount_dl());
                        tvCount.setText(count);
//                        tvContent.setText(profitInfo.getGame_desc());
                        String scoreStr = getApplicationContext().getString(R.string.active_list_score, profitInfo.getDl_back_jifen());
//                        tvScore.setText(scoreStr);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                Toast.makeText(ProfitInfoActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void shared() {
        ShareSDK.initSDK(ProfitInfoActivity.this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权,某些平台，比如新浪微博，如果不关闭sso授权，会使用已经登陆的账户进行分享
        //如果关闭，则会要求你重新登陆
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/bbt2.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


}
