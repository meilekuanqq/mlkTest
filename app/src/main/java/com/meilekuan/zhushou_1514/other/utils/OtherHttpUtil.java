package com.meilekuan.zhushou_1514.other.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * function ：请求版本等接口操作
 * author：Meilekuan
 * date: 2016/1/14 16:23
 */

public class OtherHttpUtil {

    /**
     * 请求版本的url
     */
    public static final String URL_UPGRADE = "http://zhushou.72g.com/app/common/upgrade";

    /**
     * 执行请求版本操作
     *
     * @param version  版本号
     * @param callback 请求回调
     */
    public static void requestVersion(final String version, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {

                Map<String, String> params = new HashMap<>();

                params.put("platform", "2");
                params.put("ver", version);

                return ZhuShouHttpUtil.doPost(URL_UPGRADE, params);
            }
        };

        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }

    /**
     * 下载apk
     *
     * @param url      下载地址
     * @param callback 下载回调
     */
    public static void downLoadApk(final String url, ZhuShouTask.RequestCallback callback, final ZhuShouTask.UpgradeProgress upgradeProgress) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {

                return ZhuShouHttpUtil.downLoadFile(FileUtil.APK_DIR, "72G.apk", url,upgradeProgress);
            }
        };

        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();

    }
}
