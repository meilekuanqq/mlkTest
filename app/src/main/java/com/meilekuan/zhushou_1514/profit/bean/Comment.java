package com.meilekuan.zhushou_1514.profit.bean;

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
 * date: 2016/1/17 12:34
 */

public class Comment {

    /**
     * id : 51625
     * uid : 318211287
     * content : 周末不审核的
     * img :
     * litpic :
     * pubdate : 2016-01-17 08:36
     * floor : 14
     * nickname : 伤不起
     * hpic :
     */

    private String id;
    private String uid;
    private String content;
    private String img;
    private String litpic;
    private String pubdate;
    private String floor;
    private String nickname;
    private String hpic;

    public static Comment objectFromData(String str) {

        return new Gson().fromJson(str, Comment.class);
    }

    public static Comment objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Comment.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Comment> arrayCommentFromData(String str) {

        Type listType = new TypeToken<ArrayList<Comment>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Comment> arrayCommentFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Comment>>() {
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

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHpic(String hpic) {
        this.hpic = hpic;
    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public String getLitpic() {
        return litpic;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getFloor() {
        return floor;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHpic() {
        return hpic;
    }
}
