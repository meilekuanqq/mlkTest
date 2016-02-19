package com.meilekuan.zhushou_1514.exchange.ui;

import android.content.Context;
import android.text.SpannableString;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.exchange.bean.Exchange;
import com.meilekuan.zhushou_1514.exchange.util.ExchangeHttpUtil;
import com.meilekuan.zhushou_1514.gift.bean.GiftInfo;
import com.meilekuan.zhushou_1514.other.ui.BaseActivity;
import com.meilekuan.zhushou_1514.other.utils.ImageLoader;
import com.meilekuan.zhushou_1514.other.utils.LogUtil;
import com.meilekuan.zhushou_1514.other.utils.TextUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouContans;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/14 20:30
 */

public class ExchangeInfoActivity extends BaseActivity {
    private ImageView ivHeadr;

    private TextView tvName,tvRemain,tvExchange;
    @Override
    protected int getLayout() {
        return R.layout.activity_exchange_info;
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.exchang_list_title);
        showLeftImage();

        ivHeadr = (ImageView) findViewById(R.id.exchange_image);
        tvName = (TextView) findViewById(R.id.exchange_info_name_tv);
        tvRemain = (TextView) findViewById(R.id.exchange_info_remain_tv);
        tvExchange = (TextView) findViewById(R.id.exchange_info_huan_tv);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        //获取从上一页传进来的id
        String id = getIntent().getStringExtra("id");
        ExchangeHttpUtil.requestExchangeInfo(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(ZhuShouContans.FLAG_REQUEST_STATE);
                    LogUtil.d("mlk", "state:" + state);
                    if (ZhuShouContans.FLAG_REQUEST_SUCCESS.equals(state)) {
                        JSONObject info = jsonObject.getJSONObject("info");
                        Exchange exchangeInfo = Exchange.objectFromData(info.toString());
                        LogUtil.e("mlk", "giftInfo=" + exchangeInfo);

                        ImageLoader imageLoader = new ImageLoader(ExchangeInfoActivity.this);
                        imageLoader.DisplayImage(exchangeInfo.getIcon(), ivHeadr);
                        tvName.setText(exchangeInfo.getName());

                        String remain =getApplicationContext().getString(R.string.exchang_list_info_remain, exchangeInfo.getRemain());
                        SpannableString remainS = TextUtil.getColorSpan(remain, 5, remain.length(), getApplicationContext().getResources().getColor(R.color.list_item_yellow_btn_color));
                        tvRemain.setText(remainS);

                        String exchangeStr =getApplicationContext().getString(R.string.exchang_list_info_huan, exchangeInfo.getConsume());
                        tvExchange.setText(exchangeStr);

                    }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                Toast.makeText(ExchangeInfoActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}
