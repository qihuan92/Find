package com.qihuan.find.view.i;

import com.qihuan.find.model.bean.zhihu.StoryContentEntity;
import com.qihuan.find.model.bean.zhihu.StoryExtraEntity;
import com.qihuan.find.view.i.base.IBaseView;

/**
 * IDailyDetView
 * Created by Qi on 2017/7/14.
 */

public interface IDailyDetView extends IBaseView {

    void storyContent(StoryContentEntity storyContentEntity);

    void storyExtra(StoryExtraEntity storyExtraEntity);
}
