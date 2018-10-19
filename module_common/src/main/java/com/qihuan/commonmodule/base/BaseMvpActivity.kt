package com.qihuan.commonmodule.base

import android.os.Bundle

/**
 * BaseMvpActivity
 *
 * @author qi
 * @date 2018/5/28
 */
abstract class BaseMvpActivity<in V : BaseView, out P : BasePresenter<V>> : BaseActivity(), BaseView {

    protected val mPresenter: P by lazy { initPresenter() }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
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
