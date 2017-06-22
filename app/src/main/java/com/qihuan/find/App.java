package com.qihuan.find;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

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
        initToasty();
        sharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(getApplicationContext());
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
