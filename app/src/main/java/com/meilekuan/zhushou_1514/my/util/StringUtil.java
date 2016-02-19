package com.meilekuan.zhushou_1514.my.util;

/**
 * function : 字符串工具类.
 * <p/>
 * Created by lzj on 2015/11/2.
 */
@SuppressWarnings("unused")
public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmptyOrNull(String str) {
        boolean result = false;
        if ("".equals(str) || null == str || "null".equals(str)) {
            result = true;
        }
        return result;
    }


    /**
     * 获取字符串的长度
     */
    public static int getStringLength(String str) {
        int result = 0;
        if (!isEmptyOrNull(str)) {
            result = str.length();
        }

        return result;
    }

    /**
     * 格式化字符串
     */
    public static String format(String formatStr, Object... args) {
        return String.format(formatStr, args);
    }


}
