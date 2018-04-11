package com.qihuan.find.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qihuan.commonmodule.base.BaseApp;
import com.qihuan.find.BuildConfig;

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
        initRouter();
    }

    private void initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
    }
}
