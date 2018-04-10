package com.qihuan.find.presenter;

import com.qihuan.commonmodule.utils.DateUtils;
import com.qihuan.find.contact.DailyContract;
import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.remote.ZhihuModel;
import com.qihuan.find.presenter.base.BasePresenterImpl;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DailyPresenter extends BasePresenterImpl<DailyContract.View> implements DailyContract.Presenter {

    private final ZhihuModel zhihuModel;

    public DailyPresenter() {
        zhihuModel = new ZhihuModel();
    }

    @Override
    public void getLatestDaily() {
        checkViewAttached();
        getView().showLoading();
        disposables.add(
            zhihuModel.getApi().getLatestDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(dailyBean -> getView().latestDaily(dailyBean.getTop_stories()))
                .observeOn(Schedulers.io())
                .concatMap(dailyBean -> Flowable.fromIterable(dailyBean.getStories()))
//                        .flatMap(storyBean -> Flowable.zip(Flowable.just(storyBean), zhihuApi.getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                .map(DailyItemBean::new)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    list -> {
                        list.add(0, new DailyItemBean(true, "Toady"));
                        getView().beforeDaily(list);
                        getView().onRefreshEnd();
                    },
                    e -> getView().showError(e.getMessage())
                )
        );
    }

    @Override
    public void getBeforeDaily(String date) {
        checkViewAttached();
        final DailyBean[] d = new DailyBean[1];
        disposables.add(
            zhihuModel.getApi().getBeforeDaily(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(dailyBean -> d[0] = dailyBean)
                .observeOn(Schedulers.io())
                .concatMap(dailyBean -> Flowable.fromIterable(dailyBean.getStories()))
//                        .flatMap(storyBean -> Flowable.zip(Flowable.just(storyBean), zhihuApi.getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                .map(DailyItemBean::new)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    list -> {
                        list.add(0, new DailyItemBean(true, DateUtils.parseDate(d[0].getDate())));
                        getView().beforeDaily(list);
                        getView().onLoadMoreEnd(true);
                    },
                    e -> {
                        getView().onLoadMoreEnd(false);
                        getView().showError(e.getMessage());
                    }
                )
        );
    }
}
