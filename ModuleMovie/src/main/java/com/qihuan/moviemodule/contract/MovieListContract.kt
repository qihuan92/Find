package com.qihuan.moviemodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.moviemodule.model.bean.SubjectBean

/**
 * MovieContract
 * @author qi
 * @date 2018/6/5
 */
interface MovieListContract {
    interface View : BaseView {
        fun onSubjectList(isRefresh: Boolean, subjectList: List<SubjectBean>)
    }

    interface Presenter : BasePresenter<View> {
        fun getInTheaters()
        fun getTopMovie()
        fun clearData()
    }
}