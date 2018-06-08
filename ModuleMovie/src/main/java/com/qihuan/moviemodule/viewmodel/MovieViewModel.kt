package com.qihuan.moviemodule.viewmodel

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
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

    private val movieData by lazy { MutableLiveData<MovieHomeBean>() }

    private val uiState by lazy { MutableLiveData<UIState>() }

    fun bindMovieData(owner: LifecycleOwner, onChange: (MovieHomeBean?) -> Unit) {
        movieData.observe(owner, Observer {
            onChange(it)
        })
    }

    fun bindUIState(owner: LifecycleOwner, onChange: (UIState?) -> Unit) {
        uiState.observe(owner, Observer {
            onChange(it)
        })
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
                            movieData.postValue(it)
                            uiState.postValue(UIState.FINISH)
                        },
                        onError = {
                            uiState.postValue(UIState.ERROR)
                        }
                )
    }
}