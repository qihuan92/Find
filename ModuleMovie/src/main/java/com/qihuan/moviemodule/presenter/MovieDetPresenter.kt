package com.qihuan.moviemodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.moviemodule.contract.MovieDetContract
import com.qihuan.moviemodule.model.DoubanApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MoviePresenter
 * @author qi
 * @date 2018/6/5
 */
class MovieDetPresenter : AbsRxPresenter<MovieDetContract.View>(), MovieDetContract.Presenter {

    override fun getSubject(id: String) {
        DoubanApi.get()
                .getSubject(id)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onSubject(it)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )

    }

}