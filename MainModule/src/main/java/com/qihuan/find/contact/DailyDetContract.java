package com.qihuan.find.contact;

import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;
import com.qihuan.find.view.base.BaseView;

public interface DailyDetContract {
    interface View extends BaseView {
        void storyContent(StoryContentBean storyContent);

        void storyExtra(StoryExtraBean storyExtra);

        void onFavoriteChange(boolean isFavorite);
    }

    interface Presenter {
        void getStoryContent(int id);

        void getStoryExtra(int id);

        void getFavoriteStory(int id);

        void updateFavoriteStory(int id);
    }
}
