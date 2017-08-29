package com.qihuan.find.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.model.net.Client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyViewModel
 * Created by Qi on 2017/8/28.
 */

public class DailyViewModel extends AndroidViewModel {

    public MutableLiveData<DailyBean> topDaily = new MutableLiveData<>();
    public MutableLiveData<DailyBean> beforeDaily = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();
    public MutableLiveData<Void> complete = new MutableLiveData<>();

    public DailyViewModel(Application application) {
        super(application);
    }

    public void getLatestDaily() {
        Client.getZhihuApi()
                .getLatestDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(@NonNull DailyBean dailyBean) {
                        topDaily.postValue(dailyBean);
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

    public void getBeforeDaily(String date) {
        Client.getZhihuApi()
                .getBeforeDaily(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(@NonNull DailyBean dailyBean) {
                        beforeDaily.postValue(dailyBean);
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
