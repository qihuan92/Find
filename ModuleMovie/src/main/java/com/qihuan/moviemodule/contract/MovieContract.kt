package com.qihuan.moviemodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.moviemodule.model.bean.MovieHomeBean

/**
 * MovieContract
 * @author qi
 * @date 2018/6/5
 */
interface MovieContract {
    interface View : BaseView {
        fun onData(homeBean: MovieHomeBean)
    }

    interface Presenter : BasePresenter<View> {
        fun getMovieData()
    }
}