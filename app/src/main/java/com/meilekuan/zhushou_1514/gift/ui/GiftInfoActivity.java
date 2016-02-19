package com.meilekuan.zhushou_1514.gift.ui;

import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.gift.bean.GiftInfo;
import com.meilekuan.zhushou_1514.gift.util.GiftHttpUtil;
import com.meilekuan.zhushou_1514.other.ui.BaseActivity;
import com.meilekuan.zhushou_1514.other.utils.ImageLoader;
import com.meilekuan.zhushou_1514.other.utils.LogUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouContans;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;
import com.meilekuan.zhushou_1514.other.widget.CircleImageView;
import com.meilekuan.zhushou_1514.other.widget.SharePopWindow;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/13 14:29
 */

public class GiftInfoActivity extends BaseActivity {

    private ImageView ivAndroid, ivIos, ivShare;
    private CircleImageView ivHeader;
    private TextView tvName, tvValidity, tvUbi, tvContentValue, tvGetValue, tvGameType, tvSize, tvPer;

    private ProgressBar progressBar;
    private Button btnDown;
    private View giftTypeGroup;

    @Override
    protected int getLayout() {
        return R.layout.activity_gift_info;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        setTitleText(R.string.gift_info_title);
        showLeftImage();
        setRightImage(R.drawable.selector_share);

        ivHeader = (CircleImageView) findViewById(R.id.gift_info_head_iv);

        ivAndroid = (ImageView) findViewById(R.id.gift_info_android_iv);
        ivIos = (ImageView) findViewById(R.id.gift_info_ios_iv);

        tvName = (TextView) findViewById(R.id.gift_info_name_tv);
        tvUbi = (TextView) findViewById(R.id.gift_info_ubi_tv);
        tvValidity = (TextView) findViewById(R.id.gift_info_validity_tv);
        tvContentValue = (TextView) findViewById(R.id.gift_info_content_value_tv);
        tvGetValue = (TextView) findViewById(R.id.gift_info_get_value_tv);
        tvGameType = (TextView) findViewById(R.id.gift_info_game_type_tv);
        tvSize = (TextView) findViewById(R.id.gift_info_game_size_tv);
        tvPer = (TextView) findViewById(R.id.gift_info_per_tv);

        btnDown = (Button) findViewById(R.id.gift_info_down_btn);
        giftTypeGroup = findViewById(R.id.gift_info_type_rl);

        progressBar = (ProgressBar) findViewById(R.id.gift_info_progress);

    }

    @Override
    protected void initEvents() {
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示分享菜单
                SharePopWindow popWindow = new SharePopWindow(GiftInfoActivity.this);
//                popWindow.showAsDropDown(ivHeader);
                popWindow.showAtLocation(llContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    protected void initData() {
        //获取从上一页传进来的id
        String id = getIntent().getStringExtra("id");

        GiftHttpUtil.reqestGiftInfo(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(ZhuShouContans.FLAG_REQUEST_STATE);
                    LogUtil.d("mlk", "state:" + state);
                    if (ZhuShouContans.FLAG_REQUEST_SUCCESS.equals(state)) {
                        JSONObject info = jsonObject.getJSONObject("info");
                        GiftInfo giftInfo = GiftInfo.objectFromData(info.toString());
                        LogUtil.e("mlk", "giftInfo=" + giftInfo);

                        ImageLoader imageLoader = new ImageLoader(GiftInfoActivity.this);
                        // imageLoader.DisplayImage(giftInfo.getIcon(), ivHeader);

                        String url = giftInfo.getIcon();
                        ivHeader.setImageUrl(url);

                        tvName.setText(giftInfo.getName());
                        tvUbi.setText(giftInfo.getConsume());

                        //设置U币
                        String ubi = getResources().getString(R.string.gift_info_consume, giftInfo.getConsume());
                        tvUbi.setText(ubi);
                        //设置剩余百分比
                        String per = getString(R.string.gift_info_down_per, giftInfo.getRemain(), giftInfo.getTotal());
                        tvPer.setText(per);

                        //设置进度
                        int remain = giftInfo.getRemain();
                        int total = 0;
                        try {
                            total = Integer.parseInt(giftInfo.getTotal());
                            progressBar.setMax(total);
                            progressBar.setProgress(remain);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                        //设置有效期
                        String validity = getString(R.string.gift_info_validity, giftInfo.getStime(), giftInfo.getEtime());
                        tvValidity.setText(validity);

                        //适用于那种平台
                        String platform = giftInfo.getPlatform();
                        //如果==1表示使用android
                        if ("1".equals(platform)) {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.INVISIBLE);
                        }
                        //如果==1表示使用ios
                        else if ("2".equals(platform)) {
                            ivAndroid.setVisibility(View.INVISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }
                        //其他情况适用于android和IOS
                        else {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }

                        //如果游戏类型或者游戏大小为null.那么不显示下载那一栏
                        String gameType = giftInfo.getGame_type();
                        String gameSize = giftInfo.getSize();
                        if (!(gameType == null || gameSize == null)) {
                            giftTypeGroup.setVisibility(View.VISIBLE);
                            //设置游戏类型
                            gameType = getString(R.string.gift_info_type, gameType);
                            tvGameType.setText(gameType);
                            gameSize = getString(R.string.gift_info_size, gameSize);
                            tvSize.setText(gameSize);

                        }
                        //设置游戏大小
                        String size = getString(R.string.gift_info_size, giftInfo.getSize());
                        tvSize.setText(size);

                        //去掉字符串中的html标签
                        Spanned content = Html.fromHtml(giftInfo.getContent());
                        tvContentValue.setText(content.toString());

                        Spanned get = Html.fromHtml(giftInfo.getHowget());
                        tvGetValue.setText(get.toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
            }
        });
    }

}
