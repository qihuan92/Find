package com.qihuan.commonmodule.utils

import android.content.Context
import android.support.v4.app.Fragment
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

fun Fragment.toastInfo(content: String) {
    context?.let {
        Toasty.info(it, content, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toastError(content: String) {
    context?.let {
        Toasty.error(it, content, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toastSuccess(content: String) {
    context?.let {
        Toasty.success(it, content, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toastWarning(content: String) {
    context?.let {
        Toasty.warning(it, content, Toast.LENGTH_SHORT).show()
    }
}