package com.meilekuan.zhushou_1514.gift.bean;

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
 * date: 2016/1/12 17:16
 */

public class Gift {

    /**
     * id : 5009
     * name : 《逆天仙尊》删测强化礼包
     * gamename : 逆天仙尊
     * icon : http://i5.72g.com/upload/201511/201511161438067868.png
     * remain : 30
     * gifttype : 1
     * consume : 0
     * content : 强化石*100、元宝*100
     */

    private String id;
    private String name;
    private String gamename;
    private String icon;
    private int remain;
    private String gifttype;
    private String consume;
    private String content;

    public static Gift objectFromData(String str) {

        return new Gson().fromJson(str, Gift.class);
    }

    public static Gift objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Gift.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Gift> arrayGiftFromData(String str) {

        Type listType = new TypeToken<ArrayList<Gift>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Gift> arrayGiftFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Gift>>() {
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

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public void setGifttype(String gifttype) {
        this.gifttype = gifttype;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGamename() {
        return gamename;
    }

    public String getIcon() {
        return icon;
    }

    public int getRemain() {
        return remain;
    }

    public String getGifttype() {
        return gifttype;
    }

    public String getConsume() {
        return consume;
    }

    public String getContent() {
        return content;
    }
}
