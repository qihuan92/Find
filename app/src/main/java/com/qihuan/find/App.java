package com.qihuan.find;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

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
        sharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(getApplicationContext());
    }

    public static SharedPrefsCookiePersistor getSharedPrefsCookiePersistor() {
        return sharedPrefsCookiePersistor;
    }

}
