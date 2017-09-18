package com.qihuan.find;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qihuan.find.common.GlideStrategy;
import com.qihuan.imageloader.LoaderStrategy;

import es.dmoral.toasty.Toasty;

/**
 * App
 * Created by Qi on 2017/6/20.
 */

public class App extends Application {

    private static SharedPrefsCookiePersistor sharedPrefsCookiePersistor;
    private static GlideStrategy glideStrategy;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        initARouter();
        initToasty();
        sharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(getApplicationContext());
        glideStrategy = new GlideStrategy();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initToasty() {
        Toasty.Config
                .getInstance()
                .setInfoColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                .apply();
    }

    public static SharedPrefsCookiePersistor getSharedPrefsCookiePersistor() {
        return sharedPrefsCookiePersistor;
    }

    public static LoaderStrategy imageLoaderStrategy() {
        return glideStrategy;
    }
}
