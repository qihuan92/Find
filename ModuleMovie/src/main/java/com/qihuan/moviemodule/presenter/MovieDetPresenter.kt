package com.qihuan.moviemodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.commonmodule.collection.bean.CollectionBean
import com.qihuan.commonmodule.collection.model.CollectionModel
import com.qihuan.moviemodule.contract.MovieDetContract
import com.qihuan.moviemodule.model.DoubanApi
import com.qihuan.moviemodule.model.bean.PersonBean
import com.qihuan.moviemodule.model.bean.SubjectBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MoviePresenter
 * @author qi
 * @date 2018/6/5
 */
class MovieDetPresenter : AbsRxPresenter<MovieDetContract.View>(), MovieDetContract.Presenter {

    private val collectionModel: CollectionModel = CollectionModel()
    private var subjectBean: SubjectBean? = null

    override fun getSubject(id: String) {
        DoubanApi.get()
                .getSubject(id)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            subjectBean = it
                            view?.onSubject(it)
                            // 影人
                            val actList = ArrayList<PersonBean>()
                            actList.addAll(it.directors.map {
                                it.isDirector = true
                                it
                            }.toList())
                            actList.addAll(it.casts)
                            view?.onAct(actList)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getFavoriteMovie(id: String) {
        view?.showLoading()
        collectionModel.getFavoriteList(id, 1) {
            view?.run {
                onFavoriteChange(it.isNotEmpty())
                hideLoading()
            }
        }
    }

    override fun updateFavoriteMovie(id: String) {
        subjectBean?.run {
            CollectionBean(collectionId = id, title = title, img = images.medium, type = 1).let {
                collectionModel.updateFavorite(it) {
                    view?.run {
                        hideLoading()
                        onFavoriteChange(it)
                        showUpdateFavoriteInfo(it)
                    }
                }
            }
        }
    }

}