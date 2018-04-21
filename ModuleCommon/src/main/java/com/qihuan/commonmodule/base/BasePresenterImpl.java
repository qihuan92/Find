package com.qihuan.commonmodule.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * BasePresenterImpl
 *
 * @author Qi
 */
public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private WeakReference<V> viewWeakReference;
    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void attachView(V view) {
        this.viewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (disposables != null) {
            disposables.clear();
            this.disposables = null;
        }
        if (isViewAttached()) {
            viewWeakReference.clear();
            viewWeakReference = null;
        }
    }

    public V getView() {
        return this.viewWeakReference.get();
    }

    private boolean isViewAttached() {
        return viewWeakReference != null && viewWeakReference.get() != null;
    }
}
