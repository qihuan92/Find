package com.qihuan.moviemodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.moviemodule.model.bean.SubjectBean

/**
 * MovieContract
 * @author qi
 * @date 2018/6/5
 */
interface MovieDetContract {
    interface View : BaseView {
        fun onSubject(subjectBean: SubjectBean)
    }

    interface Presenter : BasePresenter<View> {
        fun getSubject(id: String)
    }
}