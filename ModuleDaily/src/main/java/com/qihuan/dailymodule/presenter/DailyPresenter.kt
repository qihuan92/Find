package com.qihuan.dailymodule.presenter

import com.qihuan.commonmodule.base.BasePresenterImpl
import com.qihuan.commonmodule.utils.getNowDate
import com.qihuan.commonmodule.utils.parseDate
import com.qihuan.commonmodule.utils.timeSub
import com.qihuan.dailymodule.contract.DailyContract
import com.qihuan.dailymodule.model.ApiFactory
import com.qihuan.dailymodule.model.bean.DailyItemBean
import com.qihuan.dailymodule.model.bean.StoryBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * DailyPresenter
 *
 * @author Qi
 */
class DailyPresenter : BasePresenterImpl<DailyContract.View>(), DailyContract.Presenter {

    private var date: String = getNowDate()

    override fun getLatestDaily() {
        view!!.showLoading()
        date = getNowDate()
        addDisposable(
                ApiFactory.api
                        .getLatestDaily()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext({ dailyBean -> view!!.latestDaily(dailyBean.top_stories) })
                        .observeOn(Schedulers.io())
                        .concatMap({ dailyBean -> Observable.fromIterable<StoryBean>(dailyBean.stories) })
                        //.flatMap(storyBean -> Observable.zip(Observable.just(storyBean), ApiFactory.getApi().getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                        .map({ DailyItemBean(it) })
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { list ->
                                    view!!.beforeDaily(true, list)
                                    view!!.onRefreshEnd(true)
                                },
                                { e ->
                                    view!!.onRefreshEnd(false)
                                    view!!.showError(e.message ?: "")
                                }
                        )
        )
    }

    override fun getBeforeDaily() {
        date = timeSub(date)
        addDisposable(
                ApiFactory.api
                        .getBeforeDaily(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .concatMap({ dailyBean -> Observable.fromIterable<StoryBean>(dailyBean.stories) })
                        //.flatMap(storyBean -> Observable.zip(Observable.just(storyBean), ApiFactory.getApi().getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                        .map({ DailyItemBean(it) })
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { list ->
                                    list.add(0, DailyItemBean(true, parseDate(date)))
                                    view!!.beforeDaily(false, list)
                                    view!!.onLoadMoreEnd(true)
                                },
                                { e ->
                                    view!!.onLoadMoreEnd(false)
                                    view!!.showError(e.message ?: "")
                                }
                        )
        )
    }
}
