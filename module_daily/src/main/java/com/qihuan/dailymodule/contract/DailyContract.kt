package com.qihuan.dailymodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.dailymodule.model.bean.StoryBean
import com.qihuan.dailymodule.model.bean.TopStoryBean

interface DailyContract {
    interface View : BaseView {
        fun onBannerData(itemList: Array<TopStoryBean>)

        fun onDailyData(itemList: Array<StoryBean>)

        fun onDailySectionData(item: String)

        fun onRefreshStart()

        fun onRefreshEnd(success: Boolean)

        fun onLoadMoreEnd(success: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getLatestDaily()

        fun getBeforeDaily()
    }
}
