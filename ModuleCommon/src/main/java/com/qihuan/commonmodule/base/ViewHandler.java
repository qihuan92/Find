package com.qihuan.commonmodule.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mvp 中 View 的动态代理
 *
 * @author Qi
 */
public class ViewHandler<V extends BaseView> implements InvocationHandler {

    private V view;
    private BasePresenter presenter;

    ViewHandler(BasePresenter presenter, V view) {
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (presenter.isViewAttached()) {
            return method.invoke(view, args);
        }
        return null;
    }
}
