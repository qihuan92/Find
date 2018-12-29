package com.qihuan.moviemodule.model.bean

/**
 * MovieHomeBean
 * @author qi
 * @date 2018/6/6
 */
data class MovieHomeBean(
        var inTheaters: MoviesBean = MoviesBean(),
        var topMovie: MoviesBean = MoviesBean(),
        var usBox: USboxBean = USboxBean()
)