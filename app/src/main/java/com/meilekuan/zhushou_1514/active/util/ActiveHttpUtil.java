package com.meilekuan.zhushou_1514.active.util;

import com.meilekuan.zhushou_1514.other.utils.ZhuShouHttpUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/15 16:47
 */

public class ActiveHttpUtil {
    /**
     * 请求社区活动列表的url
     */
    public static final String URL_ACTIVE_LIST = "http://zhushou.72g.com/app/activity/activity_list/";
    /**
     * 请求社区活动详情的url
     */
    public static final String URL_ACTIVE_INFO = "http://zhushou.72g.com/app/activity/activity_info/";

    /**
     * 请求礼包列表
     * @param callback 请求回调
     */
    public static void requestActiveList(ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("platform", "2");
                return ZhuShouHttpUtil.doPost(URL_ACTIVE_LIST, params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }
    /**
     * 请求礼包列表
     * @param callback 请求回调
     */
    public static void requestActiveInfo(final String id,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(URL_ACTIVE_INFO, params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }
}
