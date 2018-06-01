package com.qihuan.commonmodule.utils

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * ViewExt
 * @author qi
 * @date 2018/6/1
 */

@Suppress("UNCHECKED_CAST")
fun <V : View> Context.inflate(@LayoutRes res: Int, parent: ViewGroup? = null): V {
    return LayoutInflater.from(this).inflate(res, parent, false) as V
}