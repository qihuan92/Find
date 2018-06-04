package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class MoviesBean : Serializable {
    var count: Int = 0
    var start: Int = 0
    var total: Int = 0
    var title: String? = null

    var subjects: List<SubjectBean>? = null


}
