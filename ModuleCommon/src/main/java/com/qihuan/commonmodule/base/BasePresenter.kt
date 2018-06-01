package com.qihuan.commonmodule.base

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}
