package com.qihuan.commonmodule.base

import android.arch.lifecycle.ViewModelProviders

/**
 * BaseMvpFragment
 *
 * @author qi
 * @date 2018/5/28
 */
abstract class BaseMvvmFragment<out VM : AbsRxViewModel>(clazz: Class<VM>) : BaseFragment() {
    protected val mViewModel by lazy { ViewModelProviders.of(this).get(clazz) }
}
