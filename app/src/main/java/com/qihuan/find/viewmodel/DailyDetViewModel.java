package com.qihuan.find.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;
import com.qihuan.find.model.net.Client;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyDetViewModel
 * Created by Qi on 2017/8/29.
 */

public class DailyDetViewModel extends AndroidViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<StoryContentBean> storyContent = new MutableLiveData<>();
    public MutableLiveData<StoryExtraBean> storyExtra = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    public DailyDetViewModel(Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    public void getStoryContent(int id) {
        disposables.add(
                Client.getZhihuApi()
                        .getStoryContent(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                storyContentBean -> storyContent.postValue(storyContentBean),
                                throwable -> error.postValue(throwable)
                        )
        );
    }

    public void getStoryExtra(int id) {
        disposables.add(
                Client.getZhihuApi()
                        .getStoryExtra(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                storyExtraBean -> storyExtra.postValue(storyExtraBean),
                                throwable -> error.postValue(throwable)
                        )
        );
    }
}
