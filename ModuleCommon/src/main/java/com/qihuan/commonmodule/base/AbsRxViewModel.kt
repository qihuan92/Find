package com.qihuan.commonmodule.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * AbsRxViewModel
 * @author qi
 * @date 2018/6/8
 */
abstract class AbsRxViewModel : ViewModel() {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}