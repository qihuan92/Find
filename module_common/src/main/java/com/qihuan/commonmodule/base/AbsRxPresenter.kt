package com.qihuan.commonmodule.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * AbsRxPresenter
 * @author qi
 * @date 2018/6/1
 */
abstract class AbsRxPresenter<V : BaseView>: AbsPresenter<V>() {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun detachView() {
        disposables.clear()
        super.detachView()
    }
}