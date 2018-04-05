package com.qihuan.find.presenter.base;

import com.qihuan.find.view.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private V view;
    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposables.clear();
        this.view = null;
    }

    public V getView() {
        return this.view;
    }

    private boolean isViewAttached() {
        return view != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

    public static class ViewNotAttachedException extends RuntimeException {
        ViewNotAttachedException() {
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }
}
