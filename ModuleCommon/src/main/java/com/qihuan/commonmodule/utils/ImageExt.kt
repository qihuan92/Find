package com.qihuan.commonmodule.utils

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.qihuan.commonmodule.imageloader.glide.GlideApp
import com.qihuan.commonmodule.imageloader.glide.GlideRequest

/**
 * ImageUtils
 * @author qi
 * @date 2018/5/31
 */
fun ImageView.load(url: String, radius: Float = 0f, @DrawableRes placeholder: Int = android.R.color.transparent) {
    if (radius > 0) {
        get(url).placeholder(placeholder)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(context.dp2px(radius))))
                .into(this)
        return
    }
    get(url).placeholder(placeholder)
            .centerCrop()
            .into(this)
}

fun ImageView.load(drawable: Drawable, radius: Float = 0f, @DrawableRes placeholder: Int = android.R.color.transparent) {
    if (radius > 0) {
        get(drawable).placeholder(placeholder)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(context.dp2px(radius))))
                .into(this)
        return
    }
    get(drawable).placeholder(placeholder)
            .centerCrop()
            .into(this)
}

fun ImageView.get(url: String): GlideRequest<Drawable> = GlideApp.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade())

fun ImageView.get(drawable: Drawable): GlideRequest<Drawable> = GlideApp.with(context).load(drawable).transition(DrawableTransitionOptions.withCrossFade())