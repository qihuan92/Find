package com.qihuan.dailymodule.model.bean

import java.io.Serializable


class StoryBean : Serializable {
    var type: Int = 0
    var id: Int = 0
    var ga_prefix: String = ""
    var title: String = ""
    var images: List<String> = ArrayList()
    var storyExtraBean: StoryExtraBean = StoryExtraBean()
}
