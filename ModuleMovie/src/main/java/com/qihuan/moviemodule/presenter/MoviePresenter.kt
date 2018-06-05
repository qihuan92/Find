package com.qihuan.moviemodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.moviemodule.contract.MovieContract
import com.qihuan.moviemodule.model.DoubanApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MoviePresenter
 * @author qi
 * @date 2018/6/5
 */
class MoviePresenter : AbsRxPresenter<MovieContract.View>(), MovieContract.Presenter {

    override fun getInTheaters() {
        DoubanApi.get()
                .getInTheaters()
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onInTheaters(it)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getTopMovie() {
        DoubanApi.get()
                .getTopMovie()
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onTopMovie(it)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getUsBox() {
        DoubanApi.get()
                .getUsBox()
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onUsBox(it)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

}