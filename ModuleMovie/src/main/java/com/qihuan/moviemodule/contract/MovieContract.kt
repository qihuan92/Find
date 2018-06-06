package com.qihuan.moviemodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.moviemodule.model.bean.MovieSectionItemBean
import com.qihuan.moviemodule.model.bean.MoviesBean

/**
 * MovieContract
 * @author qi
 * @date 2018/6/5
 */
interface MovieContract {
    interface View : BaseView {
        fun onData(inTheaters: MoviesBean, sectionItemList: List<MovieSectionItemBean>)
    }

    interface Presenter : BasePresenter<View> {
        fun getMovieData()
    }
}