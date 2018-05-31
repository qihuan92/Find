package com.qihuan.commonmodule.utils

import android.app.Application
import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.Toast

import com.qihuan.commonmodule.R

import es.dmoral.toasty.Toasty

/**
 * ToastUtils
 * Created by Qi on 2017/6/22.
 */

fun Context.toastInfo(content: String) {
    Toasty.info(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toastError(content: String) {
    Toasty.error(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toastSuccess(content: String) {
    Toasty.success(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toastWarning(content: String) {
    Toasty.warning(this, content, Toast.LENGTH_SHORT).show()
}

fun Application.initToast() {
    Toasty.Config
            .getInstance()
            .setInfoColor(ContextCompat.getColor(this, R.color.grey))
            .apply()
}