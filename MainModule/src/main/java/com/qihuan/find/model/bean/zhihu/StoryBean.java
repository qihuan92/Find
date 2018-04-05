package com.qihuan.find.model.bean.zhihu;

import java.io.Serializable;
import java.util.List;


public class StoryBean implements Serializable {

    /**
     * images : ["http://pic4.zhimg.com/5615a788a32c2cc9469f825c4622879b.jpg"]
     * type : 0
     * id : 9115087
     * ga_prefix : 010222
     * title : 小事 · 悲喜无常
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;
    private StoryExtraBean storyExtraBean;

    public int getType() {
        return type;
    }

    public StoryBean setType(int type) {
        this.type = type;
        return this;
    }

    public int getId() {
        return id;
    }

    public StoryBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public StoryBean setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public StoryBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public StoryBean setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public StoryExtraBean getStoryExtraBean() {
        return storyExtraBean;
    }

    public StoryBean setStoryExtraBean(StoryExtraBean storyExtraBean) {
        this.storyExtraBean = storyExtraBean;
        return this;
    }

    @Override
    public String toString() {
        return "StoryBean{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }
}
