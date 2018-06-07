package com.qihuan.moviemodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.moviemodule.model.bean.PersonBean
import com.qihuan.moviemodule.model.bean.SubjectBean

/**
 * MovieContract
 * @author qi
 * @date 2018/6/5
 */
interface MovieDetContract {
    interface View : BaseView {
        fun onSubject(subjectBean: SubjectBean)
        fun onAct(actList: List<PersonBean>)
        fun onFavoriteChange(isFavorite: Boolean)
        fun showUpdateFavoriteInfo(isFavorite: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getSubject(id: String)
        fun getFavoriteMovie(id: String)
        fun updateFavoriteMovie(id: String)
    }
}