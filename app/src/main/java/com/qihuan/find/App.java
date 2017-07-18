package com.qihuan.find;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tencent.smtt.sdk.QbSdk;

import es.dmoral.toasty.Toasty;

/**
 * App
 * Created by Qi on 2017/6/20.
 */

public class App extends Application {

    private static SharedPrefsCookiePersistor sharedPrefsCookiePersistor;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        initARouter();
        initToasty();
        initX5();
        sharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(getApplicationContext());
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(getApplicationContext(), null);
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

}
