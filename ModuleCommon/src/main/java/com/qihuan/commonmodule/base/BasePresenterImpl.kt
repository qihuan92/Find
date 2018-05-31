package com.qihuan.commonmodule.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * BasePresenterImpl
 *
 * @author Qi
 */
abstract class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

    private var viewWeakReference: WeakReference<V>? = null
    var view: V? = null
        private set
    private var disposables: CompositeDisposable? = null

    override val isViewAttached: Boolean
        get() = viewWeakReference != null && viewWeakReference!!.get() != null

    override fun attachView(view: V) {
        this.viewWeakReference = WeakReference(view)
        this.view = viewWeakReference!!.get()
    }

    override fun detachView() {
        if (disposables != null) {
            disposables!!.clear()
            this.disposables = null
        }
        if (isViewAttached) {
            viewWeakReference!!.clear()
            viewWeakReference = null
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        if (disposables == null) {
            disposables = CompositeDisposable()
        }
        disposables!!.add(disposable)
    }

}
