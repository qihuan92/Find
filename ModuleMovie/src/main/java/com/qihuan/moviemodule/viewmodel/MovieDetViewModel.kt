package com.qihuan.moviemodule.viewmodel

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.qihuan.commonmodule.base.AbsRxViewModel
import com.qihuan.commonmodule.collection.bean.CollectionBean
import com.qihuan.commonmodule.collection.model.CollectionModel
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
class MovieDetViewModel : AbsRxViewModel() {

    private val collectionModel: CollectionModel = CollectionModel()
    private var subjectBean: SubjectBean? = null

    enum class UIState {
        LOADING, ERROR, FINISH
    }

    private val subjectData by lazy { MutableLiveData<SubjectBean>() }

    private val actListData by lazy { MutableLiveData<List<PersonBean>>() }

    private val uiState by lazy { MutableLiveData<UIState>() }

    private val isFavorite by lazy { MutableLiveData<Boolean>() }

    fun bindMovieData(owner: LifecycleOwner, onChange: (SubjectBean?) -> Unit) {
        subjectData.observe(owner, Observer {
            onChange(it)
        })
    }

    fun bindActData(owner: LifecycleOwner, onChange: (List<PersonBean>) -> Unit) {
        actListData.observe(owner, Observer {
            it?.let(onChange)
        })
    }

    fun bindFavoriteData(owner: LifecycleOwner, onChange: (Boolean?) -> Unit) {
        isFavorite.observe(owner, Observer {
            onChange(it)
        })
    }

    fun bindUIState(owner: LifecycleOwner, onChange: (UIState?) -> Unit) {
        uiState.observe(owner, Observer {
            onChange(it)
        })
    }

    fun getSubject(id: String) {
        DoubanApi.get()
                .getSubject(id)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            subjectBean = it
                            subjectData.postValue(it)
                            // 影人
                            val actList = ArrayList<PersonBean>()
                            actList.addAll(it.directors.map {
                                it.isDirector = true
                                it
                            }.toList())
                            actList.addAll(it.casts)
                            actListData.postValue(actList)
                            uiState.postValue(UIState.FINISH)
                        },
                        onError = {
                            uiState.postValue(UIState.ERROR)
                        }
                )
    }

    fun getFavoriteMovie(id: String) {
        uiState.postValue(UIState.LOADING)
        collectionModel.getFavoriteList(id, 1) {
            isFavorite.postValue(it.isNotEmpty())
            uiState.postValue(UIState.FINISH)
        }
    }

    fun updateFavoriteMovie(id: String) {
        uiState.postValue(UIState.LOADING)
        subjectBean?.run {
            CollectionBean(collectionId = id, title = title, img = images.medium, type = 1).let {
                collectionModel.updateFavorite(it) {
                    uiState.postValue(UIState.FINISH)
                    isFavorite.postValue(it)
                }
            }
        }
    }

}