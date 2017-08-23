package com.qihuan.find.presenter;

import com.qihuan.find.model.bean.zhihu.StoryContentEntity;
import com.qihuan.find.model.bean.zhihu.StoryExtraEntity;
import com.qihuan.find.model.net.Client;
import com.qihuan.find.presenter.base.BasePresenter;
import com.qihuan.find.presenter.base.PresenterEvent;
import com.qihuan.find.view.i.IDailyDetView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DailyDetPresenter
 * Created by Qi on 2017/6/22.
 */

public class DailyDetPresenter extends BasePresenter<IDailyDetView> {

    public void getStoryContent(int id) {
        Client.getZhihuApi()
                .getStoryContent(id)
                .compose(this.bindUntilEvent(PresenterEvent.DETACHED))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryContentEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getView().start();
                    }

                    @Override
                    public void onNext(@NonNull StoryContentEntity storyContentEntity) {
                        getView().storyContent(storyContentEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().end();
                    }
                });
    }

    public void getStoryExtra(int id) {
        Client.getZhihuApi()
                .getStoryExtra(id)
                .compose(this.bindUntilEvent(PresenterEvent.DETACHED))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryExtraEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getView().start();
                    }

                    @Override
                    public void onNext(@NonNull StoryExtraEntity storyExtraEntity) {
                        getView().storyExtra(storyExtraEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().end();
                    }
                });
    }


}
