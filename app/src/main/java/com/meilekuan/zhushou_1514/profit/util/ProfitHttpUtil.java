package com.meilekuan.zhushou_1514.profit.util;

import android.widget.Toast;

import com.meilekuan.zhushou_1514.gift.bean.Gift;
import com.meilekuan.zhushou_1514.gift.util.GiftHttpUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouHttpUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/15 12:24
 */

public class ProfitHttpUtil  {
    /**
     * 请求游戏列表的url
     */
    public static final String URL_GIFT_LIST = "http://zhushou.72g.com/app/game/game_list/";
    /**
     * 请求游戏列表的url
     */
    public static final String URL_GIFT_INFO_LIST = "http://zhushou.72g.com/app/game/game_info/";
    /**
     * 请求游戏列表的url
     */
    public static final String URL_COMMENT= "http://zhushou.72g.com/app/game/game_comment_list/";



    /**
     * 请求礼包列表
     * @param callback 请求回调
     */
    public static void requestProfitList(final int page,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("platform", "2");
                params.put("page", " "+page);
                return ZhuShouHttpUtil.doPost(URL_GIFT_LIST, params);
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
    public static void requestProfitInfoList(final String id,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(URL_GIFT_INFO_LIST, params);
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
    public static void requestCommentList(final String id,ZhuShouTask.RequestCallback callback){
        //创建一个请求
        ZhuShouTask.Request request = new ZhuShouTask.Request(){
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(URL_COMMENT, params);
            }
        };

        //创建一个任务
        ZhuShouTask task = new ZhuShouTask(request,callback);
        //执行任务
        task.execute();
    }


}
