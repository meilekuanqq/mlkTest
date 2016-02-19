package com.meilekuan.zhushou_1514.my.util;

import java.net.URLEncoder;

/**
 * @author tengtao
 * @time ${DATA} 17:57
 * @des ${常用工具类}
 */
public class Util {




    /** http请求的中文参数进行URL编码 */
    public static String urlEncoded(String paramString) {
        if (StringUtil.isEmptyOrNull(paramString)) {
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return "";
    }

    /** 是否是手号码 */
    public static boolean isTelNumber(String telnum) {
        return telnum.matches("^1\\d{10}$");
    }

    /** 隐藏手号码中间四位 */
    public static String formatPhoneNumber(String telnum) {
        if (telnum == null || telnum.length() == 0) {
            return null;
        }
        if (!isTelNumber(telnum)) {
            return telnum;
        }
        return telnum.substring(0, 3) + "****" + telnum.substring(7, telnum.length());
    }

}
