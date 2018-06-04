package com.qihuan.commonmodule.utils

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * ViewExt
 * @author qi
 * @date 2018/6/1
 */

@Suppress("UNCHECKED_CAST")
fun <V : View> Context.inflate(@LayoutRes res: Int, parent: ViewGroup? = null): V {
    return LayoutInflater.from(this).inflate(res, parent, false) as V
}

// TabLayout 条目重复点击事件
fun TabLayout.setItemReselectedListener(position: Int, listener: () -> Unit) {
    val container = getChildAt(0) as LinearLayout
    container.getChildAt(position).setOnClickListener {
        if (selectedTabPosition == position) {
            listener()
        }
    }
}