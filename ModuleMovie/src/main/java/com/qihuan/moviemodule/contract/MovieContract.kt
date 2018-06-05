package com.qihuan.moviemodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.USboxBean

/**
 * MovieContract
 * @author qi
 * @date 2018/6/5
 */
interface MovieContract {
    interface View : BaseView {
        fun onInTheaters(moviesBean: MoviesBean)
        fun onTopMovie(moviesBean: MoviesBean)
        fun onUsBox(usboxBean: USboxBean)
    }

    interface Presenter : BasePresenter<View> {
        fun getInTheaters()
        fun getTopMovie()
        fun getUsBox()
    }
}