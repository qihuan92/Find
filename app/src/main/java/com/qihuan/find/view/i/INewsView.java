package com.qihuan.find.view.i;

import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.view.i.base.IBaseView;

/**
 * INewsView
 * Created by Qi on 2017/6/22.
 */

public interface INewsView extends IBaseView {
    void topDaily(DailyBean dailyBean);

    void beforeDaily(DailyBean dailyBean);
}
