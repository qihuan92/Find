package com.qihuan.commonmodule.utils

import android.content.Context
import androidx.fragment.app.Fragment
import android.widget.Toast
import es.dmoral.toasty.Toasty

/**
 * ToastUtils
 * Created by Qi on 2017/6/22.
 */

fun Context.toastInfo(content: String) {
    Toasty.info(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toastError(content: String = "未知错误") {
    Toasty.error(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toastSuccess(content: String) {
    Toasty.success(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toastWarning(content: String) {
    Toasty.warning(this, content, Toast.LENGTH_SHORT).show()
}

fun androidx.fragment.app.Fragment.toastInfo(content: String) {
    context?.let {
        Toasty.info(it, content, Toast.LENGTH_SHORT).show()
    }
}

fun androidx.fragment.app.Fragment.toastError(content: String = "未知错误") {
    context?.let {
        Toasty.error(it, content, Toast.LENGTH_SHORT).show()
    }
}

fun androidx.fragment.app.Fragment.toastSuccess(content: String) {
    context?.let {
        Toasty.success(it, content, Toast.LENGTH_SHORT).show()
    }
}

fun androidx.fragment.app.Fragment.toastWarning(content: String) {
    context?.let {
        Toasty.warning(it, content, Toast.LENGTH_SHORT).show()
    }
}