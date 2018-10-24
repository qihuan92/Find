package com.qihuan.commonmodule.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout

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

// 设置文字
fun TextView.appendTextList(listStr: List<String>) {
    for (i in listStr.indices) {
        if (i == listStr.size - 1) {
            append(listStr[i])
        } else {
            append("${listStr[i]}/")
        }
    }
}

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Activity.parseColorRes(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun androidx.fragment.app.Fragment.parseColorRes(@ColorRes colorRes: Int): Int {
    context?.apply {
        return ContextCompat.getColor(this, colorRes)
    }
    return Color.parseColor("#00000000")
}