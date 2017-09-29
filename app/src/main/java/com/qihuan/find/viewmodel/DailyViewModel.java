package com.qihuan.find.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.qihuan.find.kit.DateKit;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.StoryBean;
import com.qihuan.find.model.bean.zhihu.TopStoryBean;
import com.qihuan.find.model.net.Result;
import com.qihuan.find.model.net.api.ZhihuApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
                        .subscribe(
                                dailyBean -> {
                                    this.topStories.postValue(dailyBean.getTop_stories());
                                    stories.postValue(transList("今日热闻", dailyBean.getStories()));
                                },
                                e -> error.postValue(result.setRefresh(true).setMsg(e.getMessage())),
                                () -> complete.postValue(result.setRefresh(true))
                        )
        );
    }

    public void getBeforeDaily() {
        date = DateKit.timeSub(date);
        disposables.add(
                zhihuApi.getBeforeDaily(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                dailyBean -> stories.postValue(transList(DateKit.parseDate(dailyBean.getDate()), dailyBean.getStories())),
                                e -> error.postValue(result.setRefresh(false).setMsg(e.getMessage())),
                                () -> complete.postValue(result.setRefresh(false))
                        )
        );
    }

    private List<DailyItemBean> transList(String title, List<StoryBean> list) {
        List<DailyItemBean> storyList = new ArrayList<>();
        storyList.add(new DailyItemBean(true, title));
        for (StoryBean storyBean : list) {
            storyList.add(new DailyItemBean(storyBean));
        }
        return storyList;
    }
}
