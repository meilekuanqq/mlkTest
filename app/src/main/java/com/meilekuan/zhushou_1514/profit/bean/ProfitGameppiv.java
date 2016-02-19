package com.meilekuan.zhushou_1514.profit.bean;

import com.google.gson.Gson;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/16 16:08
 */

public class ProfitGameppiv {

    /**
     * id : 779
     * name : 火影忍者
     * icon : http://i3.72g.com/upload/201601/201601120954055269.jpg
     * count_dl : 491
     */

    private String id;
    private String name;
    private String icon;
    private String count_dl;

    public static ProfitGameppiv objectFromData(String str) {

        return new Gson().fromJson(str, ProfitGameppiv.class);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCount_dl(String count_dl) {
        this.count_dl = count_dl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getCount_dl() {
        return count_dl;
    }
}
