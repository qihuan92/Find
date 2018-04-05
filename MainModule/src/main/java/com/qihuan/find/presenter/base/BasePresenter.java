package com.qihuan.find.presenter.base;

import com.qihuan.find.view.base.BaseView;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
