package com.qihuan.moviemodule.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.qihuan.commonmodule.base.AbsRxViewModel
import com.qihuan.moviemodule.model.DoubanApi
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.SubjectBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MovieListViewModel
 * @author qi
 * @date 2018/6/8
 */
class MovieListViewModel : AbsRxViewModel() {

    enum class UIState {
        ERROR, REFRESH_FINISH, LOAD_FINISH
    }

    private var start = 0

    private val list = ArrayList<SubjectBean>()

    private val subjectList by lazy { MutableLiveData<List<SubjectBean>>() }

    private val uiState by lazy { MutableLiveData<MovieListViewModel.UIState>() }

    fun bindMovieData(owner: LifecycleOwner, onChange: (List<SubjectBean>) -> Unit) {
        subjectList.observe(owner, Observer {
            it?.let(onChange)
        })
    }

    fun bindUIState(owner: LifecycleOwner, onChange: (UIState?) -> Unit) {
        uiState.observe(owner, Observer {
            onChange(it)
        })
    }

    fun getInTheaters() {
        DoubanApi.get()
                .getInTheaters(start = start)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = ::onSuccess,
                        onError = ::onError
                )
    }

    fun getTopMovie() {
        DoubanApi.get()
                .getTopMovie(start = start)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = ::onSuccess,
                        onError = ::onError
                )
    }

    private fun onSuccess(moviesBean: MoviesBean) {
        if (start == 0) {
            uiState.postValue(UIState.REFRESH_FINISH)
            list.clear()
        } else {
            uiState.postValue(UIState.LOAD_FINISH)
        }
        list.addAll(moviesBean.subjects)
        subjectList.postValue(list)
        start += moviesBean.subjects.size
    }

    private fun onError(@Suppress("UNUSED_PARAMETER") throwable: Throwable) {
        uiState.postValue(UIState.ERROR)
    }

    fun clearData() {
        start = 0
    }
}