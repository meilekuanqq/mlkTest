package com.meilekuan.zhushou_1514.other.utils;

import android.app.Activity;
import android.content.Intent;

import com.meilekuan.zhushou_1514.other.ui.HomeActivity;

/**
 * function ：跳转工具类
 * author：Meilekuan
 * date: 2016/1/11 17:03
 */

public class JumpManager {
    /**
     * 跳转到主页面
     * @param activity 发起页面
     */
    public static void jumpToHome(Activity activity){
        Intent intent = new Intent(activity,HomeActivity.class);
        activity.startActivity(intent);
    }
}
