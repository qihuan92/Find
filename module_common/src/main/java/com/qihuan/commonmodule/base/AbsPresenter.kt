package com.qihuan.commonmodule.base

import java.lang.ref.WeakReference

/**
 * AbsPresenter
 *
 * @author Qi
 */
abstract class AbsPresenter<V : BaseView> : BasePresenter<V> {

    private var viewWeakReference: WeakReference<V>? = null
    protected var view: V? = null
        get() = viewWeakReference?.get()

    override fun attachView(view: V) {
        this.viewWeakReference = WeakReference(view)
    }

    override fun detachView() {
        viewWeakReference?.clear()
        viewWeakReference = null
    }
}
