package com.qihuan.dailymodule.model

import com.qihuan.commonmodule.net.ApiManager

/**
 * ApiFactory
 *
 * @author qi
 * @date 2018/5/21
 */
object ApiFactory {
    val api: ZhihuApi
        get() = ApiManager.instance.getApi(ZhihuApi::class.java)
}
