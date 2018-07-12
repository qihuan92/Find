package com.qihuan.dailymodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.commonmodule.utils.getNowDate
import com.qihuan.commonmodule.utils.parseDate
import com.qihuan.commonmodule.utils.timeSub
import com.qihuan.dailymodule.contract.DailyContract
import com.qihuan.dailymodule.model.ZhihuApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * DailyPresenter
 *
 * @author Qi
 */
class DailyPresenter : AbsRxPresenter<DailyContract.View>(), DailyContract.Presenter {

    private var date: String = getNowDate()

    override fun getLatestDaily() {
        view?.showLoading()
        date = getNowDate()

        ZhihuApi.get()
                .getLatestDaily()
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onRefreshStart()
                            view?.onBannerData(it.top_stories)
                            view?.onDailyData(it.stories)
                            view?.onRefreshEnd(true)
                        },
                        onError = {
                            view?.onRefreshEnd(false)
                            view?.showError(it.message ?: "")
                        }
                )
    }

    override fun getBeforeDaily() {
        date = timeSub(date)
        ZhihuApi.get()
                .getBeforeDaily(date)
                .doOnSubscribe { addDisposable(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.onDailySectionData(parseDate(date))
                            view?.onDailyData(it.stories)
                            view?.onLoadMoreEnd(true)
                        },
                        onError = {
                            view?.onLoadMoreEnd(false)
                            view?.showError(it.message ?: "")
                        }
                )
    }
}
