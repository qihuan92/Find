package com.qihuan.dailymodule.contract;

import com.qihuan.commonmodule.base.BasePresenter;
import com.qihuan.commonmodule.base.BaseView;
import com.qihuan.dailymodule.model.bean.StoryContentBean;
import com.qihuan.dailymodule.model.bean.StoryExtraBean;

public interface DailyDetContract {
    interface View extends BaseView {
        void storyContent(StoryContentBean storyContent);

        void storyExtra(StoryExtraBean storyExtra);

        void onFavoriteChange(boolean isFavorite);

        void showUpdateFavoriteInfo(boolean isFavorite);
    }

    interface Presenter extends BasePresenter<View> {
        void getStoryContent(int id);

        void getStoryExtra(int id);

        void getFavoriteStory(int id);

        void updateFavoriteStory(int id);
    }
}
