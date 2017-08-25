package com.qihuan.find.presenter;

import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;
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
                .subscribe(new Observer<StoryContentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().start();
                    }

                    @Override
                    public void onNext(@NonNull StoryContentBean storyContentBean) {
                        getView().storyContent(storyContentBean);
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
                .subscribe(new Observer<StoryExtraBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().start();
                    }

                    @Override
                    public void onNext(@NonNull StoryExtraBean storyExtraBean) {
                        getView().storyExtra(storyExtraBean);
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
