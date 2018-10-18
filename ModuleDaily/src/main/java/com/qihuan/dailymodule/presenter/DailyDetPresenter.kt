package com.qihuan.dailymodule.presenter

import android.util.Log
import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.commonmodule.collection.CollectionBean
import com.qihuan.commonmodule.db.AppDatabase
import com.qihuan.dailymodule.contract.DailyDetContract
import com.qihuan.dailymodule.model.ZhihuApi
import com.qihuan.dailymodule.model.bean.StoryContentBean
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DailyDetPresenter : AbsRxPresenter<DailyDetContract.View>(), DailyDetContract.Presenter {

    private var storyContentBean: StoryContentBean? = null

    override fun getStoryContent(id: Int) {
        ZhihuApi.get()
                .getStoryContent(id)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            this.storyContentBean = it
                            view?.storyContent(it)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getStoryExtra(id: Int) {
        ZhihuApi.get()
                .getStoryExtra(id)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.storyExtra(it)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getFavoriteStory(id: Int) {
        AppDatabase.instance
                .collectionDao()
                .queryOne(id.toString())
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            view?.onFavoriteChange(true)
                        },
                        onError = {
                            view?.onFavoriteChange(false)
                        }
                )
    }

    override fun updateFavoriteStory(id: Int) {
        AppDatabase.instance
                .collectionDao()
                .queryOne(id.toString())
                .doOnSubscribe { addDisposable(it) }
                .flatMapCompletable {
                    return@flatMapCompletable Completable.fromAction { AppDatabase.instance.collectionDao().delete(it) }
                }
                .onErrorResumeNext {
                    return@onErrorResumeNext Completable.fromAction {
                        AppDatabase.instance.collectionDao().save(CollectionBean(id.toString(), title = storyContentBean?.title, img = storyContentBean?.image))
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onComplete = {
                            Log.e("updateFavoriteStory", "success")
                        },
                        onError = {
                            Log.e("updateFavoriteStory", "onError", it)
                        }
                )
    }

}
