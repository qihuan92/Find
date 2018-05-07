package com.qihuan.find;

import com.qihuan.commonmodule.base.BaseApp;
import com.squareup.leakcanary.LeakCanary;

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
    }

}
