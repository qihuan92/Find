package com.qihuan.commonmodule.utils

import android.content.Context
import android.widget.Toast
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