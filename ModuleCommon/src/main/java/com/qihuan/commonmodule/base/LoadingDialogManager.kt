package com.qihuan.commonmodule.base

import com.qihuan.commonmodule.views.LoadingDialog

/**
 * LoadingDialogManager
 * @author qi
 * @date 2018/6/1
 */
interface LoadingDialogManager {
    val loadingDialog: LoadingDialog

    fun showLoadingDialog() {
        loadingDialog.show()
    }

    fun hideLoadingDialog() {
        loadingDialog.hide()
    }
}