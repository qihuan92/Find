package com.qihuan.find.bean.zhihu;

import java.io.Serializable;
import java.util.List;

public class BeforeDailyEntity implements Serializable {
    private String date;
    private List<StoriesEntity> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

}
