package com.meilekuan.zhushou_1514.other.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;

/**
 * function ：分享菜单
 * author：Meilekuan
 * date: 2016/1/14 10:05
 */

public class SharePopWindow extends PopupWindow {

    private Button btn;
    public SharePopWindow(Context context) {
        super(context);
        //设置pop的内容布局
        View content = LayoutInflater.from(context).inflate(R.layout.pop_share, null);
        setContentView(content);

        //设置pop的宽高，如果不设置的话，那么不显示
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

       //设置进出动画
        setAnimationStyle(R.style.share_pop_anim);

        //点击在pop意外的区域消失
        setFocusable(true);


        btn = (Button) content.findViewById(R.id.sahre_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


}
