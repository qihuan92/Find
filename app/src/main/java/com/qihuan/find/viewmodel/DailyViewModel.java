package com.qihuan.find.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.model.net.Client;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyViewModel
 * Created by Qi on 2017/8/28.
 */

public class DailyViewModel extends AndroidViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<DailyBean> topDaily = new MutableLiveData<>();
    public MutableLiveData<DailyBean> beforeDaily = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    public DailyViewModel(Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    public void getLatestDaily() {
        disposables.add(
                Client.getZhihuApi()
                        .getLatestDaily()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                dailyBean -> topDaily.postValue(dailyBean),
                                throwable -> error.postValue(throwable)
                        )
        );
    }

    public void getBeforeDaily(String date) {
        disposables.add(
                Client.getZhihuApi()
                        .getBeforeDaily(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                dailyBean -> beforeDaily.postValue(dailyBean),
                                throwable -> error.postValue(throwable)
                        )
        );
    }
}
