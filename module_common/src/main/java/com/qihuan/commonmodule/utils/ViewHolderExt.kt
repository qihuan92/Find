package com.qihuan.commonmodule.utils

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import android.widget.ImageView
import co.lujun.androidtagview.TagContainerLayout
import com.chad.library.adapter.base.BaseViewHolder

/**
 * ViewHolderExt
 * @author qi
 * @date 2018/6/6
 */
fun BaseViewHolder.loadImage(@IdRes res: Int, url: String, radius: Float = 0f, @DrawableRes placeholder: Int = android.R.color.transparent): BaseViewHolder {
    (getView(res) as ImageView).load(url, radius, placeholder)
    return this
}

fun BaseViewHolder.addTags(@IdRes res: Int, tagList: List<String>): BaseViewHolder {
    val tagContainerLayout = getView(res) as TagContainerLayout
    tagContainerLayout.tags = tagList
    return this
}