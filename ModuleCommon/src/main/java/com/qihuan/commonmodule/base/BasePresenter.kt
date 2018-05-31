package com.qihuan.commonmodule.base

interface BasePresenter<in V : BaseView> {

    val isViewAttached: Boolean
    fun attachView(view: V)

    fun detachView()
}
