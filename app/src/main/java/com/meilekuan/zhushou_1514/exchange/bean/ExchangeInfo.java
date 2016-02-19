package com.meilekuan.zhushou_1514.exchange.bean;

import com.google.gson.Gson;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/14 20:53
 */

public class ExchangeInfo {

    /**
     * id : 559
     * name : 265G定制电源
     * consume : 60000
     * icon : http://i4.265g.com/images/201501/201501161438002343.jpg
     * remain : 37
     */

    private String id;
    private String name;
    private String consume;
    private String icon;
    private String remain;

    public static ExchangeInfo objectFromData(String str) {

        return new Gson().fromJson(str, ExchangeInfo.class);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConsume() {
        return consume;
    }

    public String getIcon() {
        return icon;
    }

    public String getRemain() {
        return remain;
    }
}
