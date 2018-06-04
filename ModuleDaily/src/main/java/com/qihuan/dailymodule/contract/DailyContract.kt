package com.qihuan.dailymodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.dailymodule.model.bean.DailyItemBean
import com.qihuan.dailymodule.model.bean.TopStoryBean

interface DailyContract {
    interface View : BaseView {
        fun latestDaily(topList: List<TopStoryBean>)

        fun beforeDaily(isRefresh: Boolean, dailyList: List<DailyItemBean>)

        fun onRefreshEnd(success: Boolean)

        fun onLoadMoreEnd(success: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getLatestDaily()

        fun getBeforeDaily()
    }
}
