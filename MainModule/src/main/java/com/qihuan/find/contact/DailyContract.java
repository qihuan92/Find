package com.qihuan.find.contact;

import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.TopStoryBean;
import com.qihuan.find.view.base.BaseView;

import java.util.List;

public interface DailyContract {
    interface View extends BaseView {
        void latestDaily(List<TopStoryBean> topList);

        void beforeDaily(List<DailyItemBean> dailyList);

        void onRefreshEnd();

        void onLoadMoreEnd(boolean success);
    }

    interface Presenter {
        void getLatestDaily();

        void getBeforeDaily(String date);
    }
}
