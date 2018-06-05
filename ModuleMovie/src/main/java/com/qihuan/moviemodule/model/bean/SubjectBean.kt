package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class SubjectBean : Serializable {
    var rating: RatingBean = RatingBean()
    var reviews_count: Int = 0
    var wish_count: Int = 0
    var douban_site: String = ""
    var year: String? = null
    var images: ImagesBean = ImagesBean()
    var alt: String = ""
    var id: String = ""
    var mobile_url: String = ""
    var title: String = ""
    var do_count: Any? = null
    var share_url: String? = null
    var seasons_count: Any? = null
    var schedule_url: String? = null
    var episodes_count: Any? = null
    var collect_count: Int = 0
    var current_season: Any? = null
    var original_title: String? = null
    var summary: String? = null
    var subtype: String? = null
    var comments_count: Int = 0
    var ratings_count: Int = 0
    var countries: List<String>? = null
    var genres: List<String>? = null
    var casts: List<CastsBean>? = null
    var directors: List<DirectorsBean>? = null
    var aka: List<String>? = null
}
