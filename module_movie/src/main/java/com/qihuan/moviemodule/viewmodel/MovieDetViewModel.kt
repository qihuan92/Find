package com.qihuan.moviemodule.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.qihuan.commonmodule.base.AbsRxViewModel
import com.qihuan.commonmodule.collection.CollectionBean
import com.qihuan.commonmodule.db.AppDatabase
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
        AppDatabase.instance
                .collectionDao()
                .queryOne(id, type = 1)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            isFavorite.postValue(true)
                        },
                        onError = {
                            isFavorite.postValue(false)
                        }
                )
    }

    fun updateFavoriteMovie(id: String) {
        AppDatabase.instance
                .collectionDao()
                .queryOne(id, type = 1)
                .doOnSubscribe { addDisposable(it) }
                .flatMap { bean ->
                    return@flatMap AppDatabase.instance
                            .collectionDao()
                            .delete(bean)
                            .map { false }
                }
                .onErrorResumeNext { _ ->
                    return@onErrorResumeNext AppDatabase.instance
                            .collectionDao()
                            .save(CollectionBean(id = id, title = subjectBean?.title, img = subjectBean?.images?.medium, type = 1))
                            .map { true }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            isFavorite.postValue(it)
                        },
                        onError = {

                        }
                )
    }

}