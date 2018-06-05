package com.qihuan.dailymodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.commonmodule.collection.bean.CollectionBean
import com.qihuan.commonmodule.collection.model.CollectionModel
import com.qihuan.dailymodule.contract.DailyDetContract
import com.qihuan.dailymodule.model.ZhihuApi
import com.qihuan.dailymodule.model.bean.StoryContentBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DailyDetPresenter : AbsRxPresenter<DailyDetContract.View>(), DailyDetContract.Presenter {

    private val collectionModel: CollectionModel = CollectionModel()
    private var storyContentBean: StoryContentBean? = null

    override fun detachView() {
        super.detachView()
        collectionModel.onDestroy()
    }

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
        view?.showLoading()
        collectionModel.getFavoriteList(id.toString()) {
            view?.run {
                onFavoriteChange(it.isNotEmpty())
                hideLoading()
            }
        }
    }

    override fun updateFavoriteStory(id: Int) {
        storyContentBean?.run {
            CollectionBean(collectionId = id.toString(), title = title, img = image).let {
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
