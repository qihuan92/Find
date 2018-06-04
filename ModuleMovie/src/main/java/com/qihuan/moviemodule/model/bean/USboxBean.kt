package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class USboxBean : Serializable {
    var date: String? = null
    var title: String? = null
    var subjects: List<SubjectsBean>? = null
}
