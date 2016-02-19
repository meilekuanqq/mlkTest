package com.meilekuan.zhushou_1514.exchange.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/14 16:06
 */

public class Exchange {

    /**
     * id : 573
     * name : 运河电热毯 双档调温安全保护单人斑点花 150*70cm
     * consume : 80000
     * icon : http://i3.265g.com/images/201510/201510211650104413.jpg
     * remain : 4
     */

    private String id;
    private String name;
    private String consume;
    private String icon;
    private String remain;

    public static Exchange objectFromData(String str) {

        return new Gson().fromJson(str, Exchange.class);
    }

    public static Exchange objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Exchange.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Exchange> arrayExchangeFromData(String str) {

        Type listType = new TypeToken<ArrayList<Exchange>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Exchange> arrayExchangeFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Exchange>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


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
