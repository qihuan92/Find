package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class USboxBean : Serializable {
    var date: String = ""
    var title: String = ""
    var subjects: List<SubjectsBean>? = null
}
