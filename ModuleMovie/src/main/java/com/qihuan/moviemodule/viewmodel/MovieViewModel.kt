package com.qihuan.moviemodule.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.qihuan.commonmodule.base.AbsRxViewModel
import com.qihuan.moviemodule.model.DoubanApi
import com.qihuan.moviemodule.model.bean.MovieHomeBean
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.USboxBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MovieViewModel
 * @author qi
 * @date 2018/6/8
 */
class MovieViewModel : AbsRxViewModel() {

    enum class UIState {
        LOADING, ERROR, FINISH
    }

    val movieInTheaters by lazy { MutableLiveData<MoviesBean>() }
    val movieTop by lazy { MutableLiveData<MoviesBean>() }
    val movieUs by lazy { MutableLiveData<USboxBean>() }
    val uiState by lazy { MutableLiveData<UIState>() }

    fun getInTheaters() {
        DoubanApi.get().getInTheaters()
                .doOnSubscribe {
                    addDisposable(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            movieInTheaters.postValue(it)
                        },
                        onError = {

                        }
                )
    }

    fun getTopMovie() {
        DoubanApi.get().getTopMovie(count = 5)
                .doOnSubscribe {
                    addDisposable(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            movieTop.postValue(it)
                        },
                        onError = {

                        }
                )
    }

    fun getUsBox() {
        DoubanApi.get().getUsBox()
                .doOnSubscribe {
                    addDisposable(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            movieUs.postValue(it)
                        },
                        onError = {

                        }
                )
    }

    fun getMovieData() {
        Observable.zip(
                DoubanApi.get().getInTheaters(),
                DoubanApi.get().getTopMovie(count = 5),
                DoubanApi.get().getUsBox(),
                Function3<MoviesBean, MoviesBean, USboxBean, MovieHomeBean> { inTheaters, topMovie, usBox ->
                    MovieHomeBean(inTheaters, topMovie, usBox)
                })
                .doOnSubscribe {
                    uiState.postValue(UIState.LOADING)
                    addDisposable(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            movieInTheaters.postValue(it.inTheaters)
                            movieTop.postValue(it.topMovie)
                            movieUs.postValue(it.usBox)
                            uiState.postValue(UIState.FINISH)
                        },
                        onError = {
                            uiState.postValue(UIState.ERROR)
                        }
                )
    }
}