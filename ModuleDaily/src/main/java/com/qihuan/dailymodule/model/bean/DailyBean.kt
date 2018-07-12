package com.qihuan.dailymodule.model.bean

import java.io.Serializable

class DailyBean : Serializable {
    var date: String = ""
    var stories: Array<StoryBean> = emptyArray()
    var top_stories: Array<TopStoryBean> = emptyArray()
}
