package com.qihuan.dailymodule.presenter;

import com.qihuan.commonmodule.base.BasePresenterImpl;
import com.qihuan.commonmodule.net.ApiManager;
import com.qihuan.commonmodule.utils.DateUtils;
import com.qihuan.dailymodule.contract.DailyContract;
import com.qihuan.dailymodule.model.ZhihuApi;
import com.qihuan.dailymodule.model.bean.DailyItemBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyPresenter
 *
 * @author Qi
 */
public class DailyPresenter extends BasePresenterImpl<DailyContract.View> implements DailyContract.Presenter {

    private String date;

    public DailyPresenter() {
        date = DateUtils.getNowDate();
    }

    @Override
    public void getLatestDaily() {
        getView().showLoading();
        date = DateUtils.getNowDate();
        addDisposable(
                ApiManager.getInstance()
                        .getApi(ZhihuApi.class)
                        .getLatestDaily()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(dailyBean -> getView().latestDaily(dailyBean.getTop_stories()))
                        .observeOn(Schedulers.io())
                        .concatMap(dailyBean -> Observable.fromIterable(dailyBean.getStories()))
//                        .flatMap(storyBean -> Observable.zip(Flowable.just(storyBean), zhihuApi.getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                        .map(DailyItemBean::new)
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                list -> {
                                    list.add(0, new DailyItemBean(true, "Toady"));
                                    getView().beforeDaily(true, list);
                                    getView().onRefreshEnd();
                                },
                                e -> getView().showError(e.getMessage())
                        )
        );
    }

    @Override
    public void getBeforeDaily() {
        date = DateUtils.timeSub(date);
        addDisposable(
                ApiManager.getInstance()
                        .getApi(ZhihuApi.class)
                        .getBeforeDaily(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .concatMap(dailyBean -> Observable.fromIterable(dailyBean.getStories()))
//                        .flatMap(storyBean -> Observable.zip(Flowable.just(storyBean), zhihuApi.getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                        .map(DailyItemBean::new)
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                list -> {
                                    list.add(0, new DailyItemBean(true, DateUtils.parseDate(date)));
                                    getView().beforeDaily(false, list);
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
