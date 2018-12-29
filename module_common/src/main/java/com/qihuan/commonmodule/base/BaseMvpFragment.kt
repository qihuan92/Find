package com.qihuan.commonmodule.base

import android.os.Bundle
import android.view.View

/**
 * BaseMvpFragment
 *
 * @author qi
 * @date 2018/5/28
 */
abstract class BaseMvpFragment<in V : BaseView, out P : BasePresenter<V>> : BaseFragment(), BaseView {

    protected val mPresenter: P by lazy { initPresenter() }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.detachView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMsg: String) {

    }

    protected abstract fun initPresenter(): P

}
