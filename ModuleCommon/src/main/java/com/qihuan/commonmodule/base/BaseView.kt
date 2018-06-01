package com.qihuan.commonmodule.base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(errorMsg: String = "")
}
