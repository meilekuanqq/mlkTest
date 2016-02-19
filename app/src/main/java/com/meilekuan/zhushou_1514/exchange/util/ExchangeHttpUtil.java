package com.meilekuan.zhushou_1514.exchange.util;

import com.meilekuan.zhushou_1514.other.utils.ZhuShouHttpUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * function ：兑换（奖品的）url都在这
 * author：Meilekuan
 * date: 2016/1/14 19:37
 */

public class ExchangeHttpUtil {
    /**
     * 请求兑换列表的url
     */
    public static final String URL_EXCHANGE_LIST = "http://www.yuu1.com/app_api/prize_list/";
    public static final String URL_EXCHANGE_INFO = "http://www.yuu1.com/app_api/prize_info/";

    /**
     * 请求兑换列表
     * @param callback 请求回调
     */
    public static void requestExchangeList(final int type,final int page,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("type", " "+type);
                params.put("page", " "+page);
                return ZhuShouHttpUtil.doPost(URL_EXCHANGE_LIST, params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }
    /**
     * 请求兑换列表详情
     * @param callback 请求回调
     */
    public static void requestExchangeInfo(final String id,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", " "+id);
                return ZhuShouHttpUtil.doPost(URL_EXCHANGE_INFO, params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }
}
