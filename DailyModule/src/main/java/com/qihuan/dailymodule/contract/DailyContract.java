package com.qihuan.dailymodule.contract;

import com.qihuan.commonmodule.base.BaseView;
import com.qihuan.dailymodule.model.bean.DailyItemBean;
import com.qihuan.dailymodule.model.bean.TopStoryBean;

import java.util.List;

public interface DailyContract {
    interface View extends BaseView {
        void latestDaily(List<TopStoryBean> topList);

        void beforeDaily(boolean isRefresh, List<DailyItemBean> dailyList);

        void onRefreshEnd();

        void onLoadMoreEnd(boolean success);
    }

    interface Presenter {
        void getLatestDaily();

        void getBeforeDaily();
    }
}
