package com.meilekuan.zhushou_1514.other.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * function ：网络请求
 * author：Meilekuan
 * date: 2016/1/12 15:03
 */

public class ZhuShouHttpUtil {

    /**
     * 进行GET请求
     *
     * @param httpUrl 请求地址
     * @return 返回结果
     */
    public static Object doGet(String httpUrl) {
        if (httpUrl == null) {
            throw new NullPointerException("GET请求参数不能为空!!!");
        }
        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(httpUrl);
            //打开一个连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //设置连接超时时间
            conn.setConnectTimeout(5000);
            //设置读取超时时间
            conn.setReadTimeout(5000);
            //设置可读操作
            conn.setDoInput(true);
            //进行连接
            conn.connect();
            //获取返回码
            int code = conn.getResponseCode();
            //如果返回码是200表示请求成功，方可作后面的操作
            if (code == HttpURLConnection.HTTP_OK) {
                inputStream = conn.getInputStream();         //字节流
                reader = new InputStreamReader(inputStream); //字符流
                bufferedReader = new BufferedReader(reader); //速度快

                StringBuffer resultBuffer = new StringBuffer(); //效率高
                String line = null;
                //循环读取每一行，判断是否为空，读完为止
                while ((line = bufferedReader.readLine()) != null) {
                    //把每一行的结果连接起来
                    resultBuffer.append(line);
                }

                String result = resultBuffer.toString();
                LogUtil.w("mlk", "请求成功 result = " + result);
                //返回请求的结果
                return result;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //请求结束后关闭流
            try {
                //如果没有联网的情况下，这些流都为null，要避免空指针异常
                if (inputStream == null) {
                    return null;
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LogUtil.e("mlk", "请求失败");
        return null;
    }

    /**
     * 进行POST请求
     *
     * @param httpUrl 请求地址
     * @param params  参数
     * @return 返回结果
     */
    public static Object doPost(String httpUrl, Map<String, String> params) {

        if (httpUrl == null || params == null) {
            throw new NullPointerException("post请求 url 或 参数不能为空!!!!");
        }

        //参数处理 把参数map转换成字符串
        //platform=2&ver=v1.2.2
        //先把map转化为set
        Set<Map.Entry<String, String>> entries = params.entrySet();
        //从set里面获取迭代器
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        StringBuffer parmasBuffer = new StringBuffer();

        //进行迭代
        while (iterator.hasNext()) {
            //获取entry
            Map.Entry<String, String> entry = iterator.next();
            //获取entry的key
            String key = entry.getKey();
            parmasBuffer.append(key);
            parmasBuffer.append("=");

            //获取entry的value进行连接
            String value = entry.getValue();
            parmasBuffer.append(value);

            parmasBuffer.append("&");
        }
        String paramsString = parmasBuffer.toString();
        //去掉最后面一个多余的 &
        paramsString = paramsString.substring(0, paramsString.length() - 1);

        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        try {
            //创建一个url
            URL url = new URL(httpUrl);
            //打开一个连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接的属性
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //开始连接
            conn.connect();
            //获取输出流
            outputStream = conn.getOutputStream();
            //向服务器写入参数
            outputStream.write(paramsString.getBytes());
            outputStream.flush();

            int code = conn.getResponseCode();
            //如果返回码== 200 表示请求写入数据成功了
            if (code == HttpURLConnection.HTTP_OK) {

                //获取输入流
                inputStream = conn.getInputStream();
                reader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(reader);

                StringBuffer resultBuffer = new StringBuffer();

                //循环读取返回的内容
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //拼接内容
                    resultBuffer.append(line);
                }
                String result = resultBuffer.toString();
                LogUtil.w("mlk", "请求成功 result = " + result);
                //返回请求的结果
                return result;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //请求结束后关闭流
            try {
                //如果没有联网的情况下，这些流都为null，要避免空指针异常
                if (inputStream == null) {
                    LogUtil.e("mlk", "请求失败");
                    return null;
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LogUtil.e("mlk", "请求失败");
        return null;
    }

    /**
     * 下载图片
     *
     * @param httpUrl 图片地址
     * @return
     */
    public static Bitmap downLoadBitmap(String httpUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.connect();

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                LogUtil.i("mlk", "图片下载成功");
                return bitmap;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.e("mlk", "请求失败");
        return null;
    }

    /**
     * 下载文件操作
     *
     * @param dir      存放目录
     * @param fileName 重命名
     * @return
     */
    public static File downLoadFile(File dir, String fileName, String apkUrl, ZhuShouTask.UpgradeProgress upgradeProgress) {

        //如果文件夹不存在，那么创建文件夹
        if (!dir.exists()) {
            dir.mkdir();
        }

        File apk = new File(dir, fileName);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(apkUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);

            conn.connect();

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                inputStream = conn.getInputStream();
                fos = new FileOutputStream(apk);

                byte[] buff = new byte[256];
                int read = 0;

                //获取文件的总大小，也就是字节数
                long total = conn.getContentLength();
                LogUtil.e("mlk", "total = " + total);
                long down = 0;

                while (true) {
                    //每次读取256的个字节
                    read = inputStream.read(buff);

                    //计算现在总共下载了多少字节
                    down += read;

                    //计算下载进度
                    int per = (int) (down * 100 / total);



                    //如果 == -1，表示文件已经读完了
                    if (read == -1) {
                        per = 100;
                        upgradeProgress.showprogress(per);
                        break;

                    } else {
                        fos.write(buff, 0, read);
                        fos.flush();

                        //如果预算的结果超过100
                        if (per > 100) {
                            per = 100;
                        }
                        LogUtil.e("mlk", "per = " + per + ",down="+down);
                        //实时更新进度
                        upgradeProgress.showprogress(per);
                    }
                }
                LogUtil.d("mlk", "下载成功");
                return apk;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream == null) {
                LogUtil.e("mlk", "请求失败");
                return null;
            }
            try {
                inputStream.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        LogUtil.e("mlk", "请求失败");
        return null;
    }
}
