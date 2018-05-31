package com.qihuan.dailymodule.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

import java.io.Serializable

/**
 * DailyItemBean
 * Created by Qi on 2017/7/12.
 */

class DailyItemBean : SectionEntity<StoryBean>, Serializable {
    constructor(isHeader: Boolean, header: String) : super(isHeader, header)

    constructor(storyBean: StoryBean) : super(storyBean)
}
