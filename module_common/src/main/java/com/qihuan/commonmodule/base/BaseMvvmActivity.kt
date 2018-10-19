package com.qihuan.commonmodule.base

import androidx.lifecycle.ViewModelProviders

/**
 * BaseMvpActivity
 *
 * @author qi
 * @date 2018/5/28
 */
abstract class BaseMvvmActivity<out VM : AbsRxViewModel>(clazz: Class<VM>) : BaseActivity() {
    protected val mViewModel by lazy { ViewModelProviders.of(this).get(clazz) }
}
