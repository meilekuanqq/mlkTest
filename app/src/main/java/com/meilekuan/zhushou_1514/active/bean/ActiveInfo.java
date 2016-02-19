package com.meilekuan.zhushou_1514.active.bean;

import com.google.gson.Gson;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/15 23:19
 */

public class ActiveInfo {

    /**
     * id : 52
     * aname : 获奖名单
     * shortname : 新游《攻城掠地》火热上线
     * hotpic : http://i3.72g.com/upload/201512/201512101040321263.jpg
     * expimg :
     * astime : 2015-12-10 10:00
     * aetime : 2015-12-16 10:00
     * content : 以下是“新游《攻城掠地》火热上线 试玩就送2000U币”活动获奖名单：

     板砖
     丶Li
     仰望那片天
     快乐路口
     六月的雨
     mirliu_20
     zuishaofeng
     zj3551550
     信hu
     6V
     小炮
     撒比
     雪纷飞
     安然乐
     妮妮
     2448443771
     爱神
     yuanmo
     362430
     2970704074
     为你而生
     魅荣
     我爱的小杰杰
     斜月三星洞
     好人好梦
     你是碎嘴子不
     敏敏


     说明：U币奖励已全部发送到所有获奖用户的优易网账户中，请查收哦！
     * isimg : 1
     * writer : 72G纱纱
     * pubdate : 2015-12-10
     * comment_total : 41
     * status : 已结束
     */

    private String id;
    private String aname;
    private String shortname;
    private String hotpic;
    private String expimg;
    private String astime;
    private String aetime;
    private String content;
    private String isimg;
    private String writer;
    private String pubdate;
    private int comment_total;
    private String status;

    public static ActiveInfo objectFromData(String str) {

        return new Gson().fromJson(str, ActiveInfo.class);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setHotpic(String hotpic) {
        this.hotpic = hotpic;
    }

    public void setExpimg(String expimg) {
        this.expimg = expimg;
    }

    public void setAstime(String astime) {
        this.astime = astime;
    }

    public void setAetime(String aetime) {
        this.aetime = aetime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsimg(String isimg) {
        this.isimg = isimg;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setComment_total(int comment_total) {
        this.comment_total = comment_total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getAname() {
        return aname;
    }

    public String getShortname() {
        return shortname;
    }

    public String getHotpic() {
        return hotpic;
    }

    public String getExpimg() {
        return expimg;
    }

    public String getAstime() {
        return astime;
    }

    public String getAetime() {
        return aetime;
    }

    public String getContent() {
        return content;
    }

    public String getIsimg() {
        return isimg;
    }

    public String getWriter() {
        return writer;
    }

    public String getPubdate() {
        return pubdate;
    }

    public int getComment_total() {
        return comment_total;
    }

    public String getStatus() {
        return status;
    }
}
