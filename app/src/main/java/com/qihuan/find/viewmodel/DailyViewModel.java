package com.qihuan.find.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.qihuan.find.kit.DateKit;
import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.TopStoryBean;
import com.qihuan.find.model.net.Result;
import com.qihuan.find.model.net.api.ZhihuApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyViewModel
 * Created by Qi on 2017/8/28.
 */

public class DailyViewModel extends AndroidViewModel {

    private CompositeDisposable disposables;
    private MutableLiveData<Result> complete;
    private MutableLiveData<Result> error;
    private MutableLiveData<List<TopStoryBean>> topStories;
    private MutableLiveData<List<DailyItemBean>> stories;
    private String date;
    private Result result;

    @Inject
    ZhihuApi zhihuApi;

    @Inject
    public DailyViewModel(Application application) {
        super(application);
        disposables = new CompositeDisposable();
        date = DateKit.getNowDate();
        topStories = new MutableLiveData<>();
        stories = new MutableLiveData<>();
        complete = new MutableLiveData<>();
        error = new MutableLiveData<>();
        result = new Result();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    public MutableLiveData<List<TopStoryBean>> topStories() {
        return topStories;
    }

    public MutableLiveData<List<DailyItemBean>> stories() {
        return stories;
    }

    public MutableLiveData<Result> complete() {
        return complete;
    }

    public MutableLiveData<Result> error() {
        return error;
    }

    public void getLatestDaily() {
        date = DateKit.getNowDate();
        disposables.add(
                zhihuApi.getLatestDaily()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(dailyBean -> topStories.postValue(dailyBean.getTop_stories()))
                        .observeOn(Schedulers.io())
                        .concatMap(dailyBean -> Flowable.fromIterable(dailyBean.getStories()))
//                        .flatMap(storyBean -> Flowable.zip(Flowable.just(storyBean), zhihuApi.getStoryExtra(storyBean.getId()), StoryBean::setStoryExtraBean))
                        .map(DailyItemBean::new)
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                list -> {
                                    list.add(0, new DailyItemBean(true, "Toady"));
                                    stories.postValue(list);
                                    complete.postValue(result.setRefresh(true));
                                },
                                e -> error.postValue(result.setRefresh(true).setMsg(e.getMessage()))
                        )
        );
    }

    public void getBeforeDaily() {
        date = DateKit.timeSub(date);
        final DailyBean[] d = new DailyBean[1];
        disposables.add(
                zhihuApi.getBeforeDaily(date)
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
                                    list.add(0, new DailyItemBean(true, DateKit.parseDate(d[0].getDate())));
                                    stories.postValue(list);
                                    complete.postValue(result.setRefresh(false));
                                },
                                e -> error.postValue(result.setRefresh(false).setMsg(e.getMessage()))
                        )
        );
    }
}
