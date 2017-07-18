package com.qihuan.find.model.bean.zhihu;

public class CommentsEntity {
    /**
     * author : 张肸
     * content : 嗯……适合养猫
     * avatar : http://pic3.zhimg.com/ce6b96754524a4991a7ef1f0ec758cce_im.jpg
     * time : 1482819056
     * id : 27645644
     * likes : 1
     * reply_to : {"content":"\u201c我喜欢你，与你无关\u201d","status":0,"id":27645442,"author":"Breathless"}
     */

    private String author;
    private String content;
    private String avatar;
    private int time;
    private int id;
    private int likes;
    private ReplyToEntity reply_to;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ReplyToEntity getReply_to() {
        return reply_to;
    }

    public void setReply_to(ReplyToEntity reply_to) {
        this.reply_to = reply_to;
    }
}