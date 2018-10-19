package com.qihuan.dailymodule.contract

import com.qihuan.commonmodule.base.BasePresenter
import com.qihuan.commonmodule.base.BaseView
import com.qihuan.dailymodule.model.bean.StoryContentBean
import com.qihuan.dailymodule.model.bean.StoryExtraBean

interface DailyDetContract {
    interface View : BaseView {
        fun storyContent(storyContent: StoryContentBean)

        fun storyExtra(storyExtra: StoryExtraBean)

        fun onFavoriteChange(isFavorite: Boolean)

        fun showUpdateFavoriteInfo(isFavorite: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun getStoryContent(id: Int)

        fun getStoryExtra(id: Int)

        fun getFavoriteStory(id: Int)

        fun updateFavoriteStory(id: Int)
    }
}
