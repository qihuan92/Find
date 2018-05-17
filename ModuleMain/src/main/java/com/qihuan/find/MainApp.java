package com.qihuan.find;

import com.qihuan.commonmodule.base.BaseApp;
import com.qihuan.commonmodule.net.Api;
import com.squareup.leakcanary.LeakCanary;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * MainApp
 *
 * @author Qi
 * @date 2017/6/20
 */
public class MainApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
        RetrofitUrlManager.getInstance().putDomain(Api.ZHIHU_DOMAIN_VALUE, Api.ZHIHU_BASE_URL);
        RetrofitUrlManager.getInstance().putDomain(Api.DOUBAN_DOMAIN_VALUE, Api.DOUBAN_BASE_URL);
    }

}
