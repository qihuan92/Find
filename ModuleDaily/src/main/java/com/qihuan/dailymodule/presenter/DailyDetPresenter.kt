package com.qihuan.dailymodule.presenter

import com.qihuan.commonmodule.base.BasePresenterImpl
import com.qihuan.commonmodule.collection.bean.CollectionBean
import com.qihuan.commonmodule.collection.model.CollectionModel
import com.qihuan.dailymodule.contract.DailyDetContract
import com.qihuan.dailymodule.model.ApiFactory
import com.qihuan.dailymodule.model.bean.StoryContentBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DailyDetPresenter : BasePresenterImpl<DailyDetContract.View>(), DailyDetContract.Presenter {

    private val collectionModel: CollectionModel = CollectionModel()
    private var storyContentBean: StoryContentBean? = null

    override fun detachView() {
        super.detachView()
        collectionModel.onDestroy()
    }

    override fun getStoryContent(id: Int) {

        addDisposable(
                ApiFactory.api
                        .getStoryContent(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    this.storyContentBean = it
                                    view!!.storyContent(it)
                                },
                                onError = {
                                    view!!.showError(it.message ?: "")
                                }
                        )
        )
    }

    override fun getStoryExtra(id: Int) {
        addDisposable(
                ApiFactory.api
                        .getStoryExtra(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    view!!.storyExtra(it)
                                },
                                onError = {
                                    view!!.showError(it.message ?: "")
                                }
                        )
        )
    }

    override fun getFavoriteStory(id: Int) {
        view!!.showLoading()

        collectionModel.getFavoriteList(id.toString()) {
            view!!.onFavoriteChange(it.isNotEmpty())
            view!!.hideLoading()
        }
    }

    override fun updateFavoriteStory(id: Int) {
        if (storyContentBean == null) {
            return
        }
        val collectionBean = CollectionBean()
        collectionBean.collectionId = id.toString()
        collectionBean.type = 0
        collectionBean.title = storyContentBean!!.title
        collectionBean.img = storyContentBean!!.image

        collectionModel.updateFavorite(collectionBean) {
            view!!.hideLoading()
            view!!.onFavoriteChange(it)
            view!!.showUpdateFavoriteInfo(it)
        }
    }

}
