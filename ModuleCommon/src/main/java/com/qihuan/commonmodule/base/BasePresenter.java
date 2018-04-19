package com.qihuan.commonmodule.base;

import com.qihuan.commonmodule.base.BaseView;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
