package com.qihuan.dailymodule.model.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.Serializable;

/**
 * DailyItemBean
 * Created by Qi on 2017/7/12.
 */

public class DailyItemBean extends SectionEntity<StoryBean> implements Serializable {
    public DailyItemBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public DailyItemBean(StoryBean storyBean) {
        super(storyBean);
    }
}
