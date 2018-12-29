package com.qihuan.dailymodule.model.bean

import java.io.Serializable

class StoryContentBean : Serializable {

    var id: Int = 0
    var isFavorite: Boolean = false
    var title: String = ""
    var type: Int = 0
    var body: String = ""
    var image: String = ""
    var image_source: String = ""
    var share_url: String = ""
    var ga_prefix: String = ""
    var js: List<String> = ArrayList()
    var images: List<String> = ArrayList()
    var css: List<String> = ArrayList()
}
