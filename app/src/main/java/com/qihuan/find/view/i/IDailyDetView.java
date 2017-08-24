package com.qihuan.find.view.i;

import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;
import com.qihuan.find.view.i.base.IBaseView;

/**
 * IDailyDetView
 * Created by Qi on 2017/7/14.
 */

public interface IDailyDetView extends IBaseView {

    void storyContent(StoryContentBean storyContentBean);

    void storyExtra(StoryExtraBean storyExtraBean);
}
