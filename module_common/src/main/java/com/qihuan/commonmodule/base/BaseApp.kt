package com.qihuan.commonmodule.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.github.moduth.blockcanary.BlockCanary
import com.qihuan.commonmodule.BuildConfig
import com.qihuan.commonmodule.net.ApiManager
import com.scwang.smartrefresh.header.DeliveryHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.squareup.leakcanary.LeakCanary
import kotlin.properties.Delegates


/**
 * BaseApp
 *
 * @author Qi
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    private fun init() {
        ApiManager.init(this)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.printStackTrace()
        }
        ARouter.init(this)
        if (BuildConfig.DEBUG) {
            // LeakCanary
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
            // BlockCanary
            BlockCanary.install(this, AppBlockCanaryContext()).start()
        }
    }

    companion object {
        var instance: BaseApp by Delegates.notNull()

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
