package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class USboxBean : Serializable {
    var date: String? = null
    var title: String? = null
    var subjects: List<SubjectsBean>? = null

    override fun toString(): String {
        return title + subjects!!.size + subjects!![1].toString()
    }
}
