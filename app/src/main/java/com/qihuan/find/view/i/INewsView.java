package com.qihuan.find.view.i;

import com.qihuan.find.bean.zhihu.DailyEntity;

/**
 * INewsView
 * Created by Qi on 2017/6/22.
 */

public interface INewsView {

    void s();

    void get(DailyEntity dailyEntity);

    void error(String message);
}
