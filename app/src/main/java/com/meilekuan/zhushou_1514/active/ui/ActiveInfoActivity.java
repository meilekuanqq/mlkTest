package com.meilekuan.zhushou_1514.active.ui;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.active.bean.ActiveInfo;
import com.meilekuan.zhushou_1514.active.util.ActiveHttpUtil;
import com.meilekuan.zhushou_1514.exchange.bean.Exchange;
import com.meilekuan.zhushou_1514.other.ui.BaseActivity;
import com.meilekuan.zhushou_1514.other.utils.LogUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouContans;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;
import com.meilekuan.zhushou_1514.other.widget.SharePopWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * function ：社区活动详情页面
 * author：Meilekuan
 * date: 2016/1/15 23:20
 */

public class ActiveInfoActivity extends BaseActivity {

    private List<ActiveInfo> list;
    private TextView tvName, tvUser, tvDate,tvContent;

    @Override
    protected int getLayout() {
        return R.layout.activity_activeinfo;
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.active_title_info);
        showLeftImage();
        setRightImage(R.drawable.selector_share);

        tvName = (TextView) findViewById(R.id.active_info_name_tv);
        tvUser = (TextView) findViewById(R.id.active_info_username_tv);
        tvDate = (TextView) findViewById(R.id.active_info_date_tv);
        tvContent = (TextView) findViewById(R.id.active_info_contetn_tv);

    }

    @Override
    protected void initEvents() {
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示分享菜单
                SharePopWindow popWindow = new SharePopWindow(ActiveInfoActivity.this);
//                popWindow.showAsDropDown(ivHeader);
                popWindow.showAtLocation(llContent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");

        ActiveHttpUtil.requestActiveInfo(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                if (result == null){
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(ZhuShouContans.FLAG_REQUEST_STATE);
                    if (ZhuShouContans.FLAG_REQUEST_SUCCESS.equals(state)){
                        JSONObject info = jsonObject.getJSONObject("info");
                        ActiveInfo activeInfo = ActiveInfo.objectFromData(info.toString());
                        LogUtil.e("mlk", "giftInfo=" + activeInfo);

                        tvName.setText(activeInfo.getAname());
                        tvUser.setText(activeInfo.getWriter());
                        tvDate.setText(activeInfo.getAstime());
                        tvContent.setText(activeInfo.getContent());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                Toast.makeText(ActiveInfoActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
