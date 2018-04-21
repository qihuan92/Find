package com.qihuan.commonmodule.base;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
