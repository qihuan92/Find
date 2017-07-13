package com.qihuan.find.view.i.base;

/**
 * IBaseView
 * Created by Qi on 2017/7/12.
 */

public interface IBaseView {
    void start();

    void end();

    void error(String throwable);
}
