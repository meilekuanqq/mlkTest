package com.meilekuan.zhushou_1514.gift.util;

import com.meilekuan.zhushou_1514.other.utils.ZhuShouHttpUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * function ：礼包模块的网路请求都在这里
 *
 * author：Meilekuan
 *
 * date: 2016/1/13 11:14
 */

public class GiftHttpUtil {
    /**
     * 请求礼包列表的url
     */
    public static final String URL_GIFT_LIST = "http://zhushou.72g.com/app/gift/gift_list/";
    /**
     * 请求礼包详情的url
     */
    public static final String URL_GIFT_INFO = "http://zhushou.72g.com/app/gift/gift_info/";

    /**
     * 请求礼包列表
     * @param callback 请求回调
     */
    public static void requestGiftList(final int type,final int page,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("platform", "2");
                params.put("gifttype", " "+type);
                params.put("page", " "+page);
                return ZhuShouHttpUtil.doPost(URL_GIFT_LIST,params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }

    /**
     * 请求礼包详情
     * @param id 礼包id
     * @param callback 请求回调
     */
    public static void reqestGiftInfo(final String id,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(URL_GIFT_INFO,params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }
}
