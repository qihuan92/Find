package com.qihuan.commonmodule.base

import com.github.moduth.blockcanary.BlockCanaryContext
import com.github.moduth.blockcanary.BuildConfig

/**
 * AppBlockCanaryContext
 * @author qi
 * @date 2018/10/24
 */
class AppBlockCanaryContext : BlockCanaryContext() {

    override fun provideQualifier(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun provideNetworkType(): String {
        return "wifi"
    }

    override fun provideBlockThreshold(): Int {
        return 800
    }
}