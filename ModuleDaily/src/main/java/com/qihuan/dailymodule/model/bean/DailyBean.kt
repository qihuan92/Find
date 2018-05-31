package com.qihuan.dailymodule.model.bean

import java.io.Serializable

class DailyBean : Serializable {
    var date: String = ""
    var stories: List<StoryBean> = ArrayList()
    var top_stories: List<TopStoryBean> = ArrayList()
}
