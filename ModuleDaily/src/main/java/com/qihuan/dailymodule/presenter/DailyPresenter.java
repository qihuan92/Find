package com.qihuan.dailymodule.presenter;

import com.qihuan.commonmodule.base.BasePresenterImpl;
import com.qihuan.commonmodule.utils.DateUtils;
import com.qihuan.dailymodule.contract.DailyContract;
import com.qihuan.dailymodule.model.ZhihuModel;
import com.qihuan.dailymodule.model.bean.DailyItemBean;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyPresenter
 *
 * @author Qi
 */
public class DailyPresenter extends BasePresenterImpl<DailyContract.View> implements DailyContract.Presenter {

    private final ZhihuModel zhihuModel;
    private String date;

    public DailyPresenter() {
        date = DateUtils.getNowDate();
        zhihuModel = new ZhihuModel();
    }

    @Override
    public void getLatestDaily() {
        getView().showLoading();
        date = DateUtils.getNowDate();
        addDisposable(
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
                zhihuModel.getApi().getBeforeDaily(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .concatMap(dailyBean -> Flowable.fromIterable(dailyBean.getStories()))
//                        .flatMap(storyBean -> Flowable.zip(Flowable.just(storyBean), zhihuApi.getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
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
