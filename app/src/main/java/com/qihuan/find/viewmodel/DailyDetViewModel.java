package com.qihuan.find.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;
import com.qihuan.find.model.net.Client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyDetViewModel
 * Created by Qi on 2017/8/29.
 */

public class DailyDetViewModel extends AndroidViewModel {

    public MutableLiveData<StoryContentBean> storyContent = new MutableLiveData<>();
    public MutableLiveData<StoryExtraBean> storyExtra = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();
    public MutableLiveData<Void> complete = new MutableLiveData<>();

    public DailyDetViewModel(Application application) {
        super(application);
    }

    public void getStoryContent(int id) {
        Client.getZhihuApi()
                .getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryContentBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(@NonNull StoryContentBean storyContentBean) {
                        storyContent.postValue(storyContentBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.postValue(e);
                    }

                    @Override
                    public void onComplete() {
                        complete.postValue(null);
                    }
                });
    }

    public void getStoryExtra(int id) {
        Client.getZhihuApi()
                .getStoryExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryExtraBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(@NonNull StoryExtraBean storyExtraBean) {
                        storyExtra.postValue(storyExtraBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.postValue(e);
                    }

                    @Override
                    public void onComplete() {
                        complete.postValue(null);
                    }
                });
    }
}
