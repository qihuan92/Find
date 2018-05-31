package com.qihuan.commonmodule.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.BuildConfig
import com.qihuan.commonmodule.collection.bean.MyObjectBox
import com.qihuan.commonmodule.net.ApiManager
import com.qihuan.commonmodule.utils.initToast
import com.scwang.smartrefresh.header.DeliveryHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import io.objectbox.BoxStore

/**
 * BaseApp
 *
 * @author Qi
 */
open class BaseApp : Application() {
    var boxStore: BoxStore? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    private fun init() {
        ApiManager.init(this)
        initToast()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.printStackTrace()
        }
        ARouter.init(this)

        boxStore = MyObjectBox.builder()
                .androidContext(this)
                .build()
    }

    companion object {

        var instance: BaseApp? = null
            private set

        init {
            // 设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
                // 指定为经典Header，默认是 贝塞尔雷达Header
                DeliveryHeader(context)
            }
            // 设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                // 指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }
}
