package com.qihuan.banner.listener

import android.view.View

/**
 * OnBannerLoadLisenter
 * @author qi
 * @date 2018/10/24
 */
interface OnBannerLoadLisenter<T> {
    fun onBannerItemLoad(view: View, data: T, position: Int)
}