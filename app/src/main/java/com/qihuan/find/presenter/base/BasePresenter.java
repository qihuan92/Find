package com.qihuan.find.presenter.base;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.OutsideLifecycleException;
import com.trello.rxlifecycle2.RxLifecycle;

import javax.annotation.Nonnull;

import easymvp.RxPresenter;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;

/**
 * BasePresenter
 * Created by Qi on 2017/6/22.
 */

public class BasePresenter<V> extends RxPresenter<V> implements LifecycleProvider<PresenterEvent> {

    private final BehaviorSubject<PresenterEvent> lifecycleSubject = BehaviorSubject.create();
    private static final Function<PresenterEvent, PresenterEvent> PRESENTER_LIFECYCLE =
            lastEvent -> {
                switch (lastEvent) {
                    case ATTACHED:
                        return PresenterEvent.DETACHED;
                    case DESTROYED:
                        throw new OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.");
                    default:
                        throw new UnsupportedOperationException("Binding to " + lastEvent + " not yet implemented");
                }
            };

    @Nonnull
    @Override
    public Observable<PresenterEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull PresenterEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bind(lifecycleSubject, PRESENTER_LIFECYCLE);
    }

    @Override
    public void onViewAttached(V view) {
        super.onViewAttached(view);
        lifecycleSubject.onNext(PresenterEvent.ATTACHED);
    }

    @Override
    public void onViewDetached() {
        lifecycleSubject.onNext(PresenterEvent.DETACHED);
        super.onViewDetached();
    }

    @Override
    public void onDestroyed() {
        lifecycleSubject.onNext(PresenterEvent.DESTROYED);
        super.onDestroyed();
    }

}
