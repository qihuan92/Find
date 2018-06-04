package com.qihuan.moviemodule.model.bean

import java.io.Serializable

class RatingBean : Serializable {
    var max: Int = 0
    var average: Double = 0.toDouble()
    var stars: String? = null
    var min: Int = 0
}
