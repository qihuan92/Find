package com.qihuan.find.presenter;

import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.model.net.Client;
import com.qihuan.find.presenter.base.BasePresenter;
import com.qihuan.find.presenter.base.PresenterEvent;
import com.qihuan.find.view.i.INewsView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * NewsPresenter
 * Created by Qi on 2017/6/22.
 */

public class NewsPresenter extends BasePresenter<INewsView> {

    public void getLatestDaily() {
        Client.getZhihuApi()
                .getLatestDaily()
                .compose(this.bindUntilEvent(PresenterEvent.DETACHED))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        getView().start();
                    }

                    @Override
                    public void onNext(@NonNull DailyBean dailyBean) {
                        getView().topDaily(dailyBean);
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

    public void getBeforeDaily(String date) {
        Client.getZhihuApi()
                .getBeforeDaily(date)
                .compose(this.bindUntilEvent(PresenterEvent.DETACHED))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        getView().start();
                    }

                    @Override
                    public void onNext(@NonNull DailyBean dailyBean) {
                        getView().beforeDaily(dailyBean);
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
