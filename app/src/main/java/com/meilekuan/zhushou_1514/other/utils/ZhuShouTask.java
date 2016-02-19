package com.meilekuan.zhushou_1514.other.utils;

import android.os.AsyncTask;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/13 10:59
 */

public class ZhuShouTask extends AsyncTask<Void, Integer, Object> {

    private Request request;

    private RequestCallback callback;

    public ZhuShouTask(Request request, RequestCallback callback) {
        if (request == null || callback == null) {
            throw new NullPointerException("request or callback can not be null...");
        }
        this.request = request;
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(Void... params) {

        return request.doRequest();
    }

    /**
     * 更新adapter
     *
     * @param result
     */
    @Override
    protected void onPostExecute(Object result) {
        /**
         * 如果请求的结果为空，表示请求失败了
         */
        if (result == null) {
            callback.error("请求失败");
        } else {
            callback.success(result);
        }
    }

    /**
     * 请求接口
     */
    public interface Request {
        /**
         * 执行请求的方法
         *
         * @return 返回请求的结果
         */
        Object doRequest();
    }

    /**
     * 请求回调接口
     */
    public interface RequestCallback {
        /**
         * 请求成功回调方法
         *
         * @param result 请求成功的结果
         */
        void success(Object result);

        /**
         * 请求失败回调方法
         *
         * @param msg 请求失败的错误信息
         */
        void error(String msg);
    }

    /**
     * 更新下载进度接口
     */
    public interface UpgradeProgress{

        /**
         * 显示进度
         * @param progress 进度百分比
         */
        void showprogress(int progress);
    }

}
