package com.qihuan.banner.listener

import android.view.View

/**
 * OnBannerItemClickListener
 * @author qi
 * @date 2018/10/24
 */
interface OnBannerItemClickListener<T> {
    fun onBannerItemClick(view: View, data: T, position: Int)
}