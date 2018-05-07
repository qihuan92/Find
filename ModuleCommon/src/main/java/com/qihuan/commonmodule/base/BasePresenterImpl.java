package com.qihuan.commonmodule.base;

import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BasePresenterImpl
 *
 * @author Qi
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private WeakReference<V> viewWeakReference;
    private V proxyView;
    private CompositeDisposable disposables;

    @Override
    public void attachView(V view) {
        this.viewWeakReference = new WeakReference<>(view);
        ViewHandler<V> viewHandler = new ViewHandler<>(this, viewWeakReference.get());
        // noinspection unchecked
        proxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), viewHandler);
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

    @Override
    public boolean isViewAttached() {
        return viewWeakReference != null && viewWeakReference.get() != null;
    }

    public V getView() {
        return this.proxyView;
    }

    protected void addDisposable(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }

}
