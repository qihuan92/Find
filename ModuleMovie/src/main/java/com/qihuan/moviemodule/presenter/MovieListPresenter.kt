package com.qihuan.moviemodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.moviemodule.contract.MovieListContract
import com.qihuan.moviemodule.model.DoubanApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MoviePresenter
 * @author qi
 * @date 2018/6/5
 */
class MovieListPresenter : AbsRxPresenter<MovieListContract.View>(), MovieListContract.Presenter {

    private var start = 0

    override fun getInTheaters() {
        DoubanApi.get()
                .getInTheaters(start = start)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onSubjectList(start == 0, it.subjects)
                            start += it.subjects.size
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getTopMovie() {
        DoubanApi.get()
                .getTopMovie(start = start)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onSubjectList(start == 0, it.subjects)
                            start += it.subjects.size
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun clearData() {
        start = 0
    }

}