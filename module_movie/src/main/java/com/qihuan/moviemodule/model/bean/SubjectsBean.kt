package com.qihuan.moviemodule.model.bean

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class SubjectsBean : Serializable {
    var box: Int = 0
    @SerializedName("new")
    var isNewX: Boolean = false
    var rank: Int = 0
    var subject: SubjectBean = SubjectBean()
}
