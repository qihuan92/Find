package com.qihuan.find;

import android.app.Application;

import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qihuan.imageloader.LoaderStrategy;

/**
 * App
 * Created by Qi on 2017/6/20.
 */

public class App extends Application {

    private static SharedPrefsCookiePersistor sharedPrefsCookiePersistor;
    private static LoaderStrategy strategy;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static SharedPrefsCookiePersistor getSharedPrefsCookiePersistor() {
        return sharedPrefsCookiePersistor;
    }

    public static void setSharedPrefsCookiePersistor(SharedPrefsCookiePersistor sharedPrefsCookiePersistor) {
        App.sharedPrefsCookiePersistor = sharedPrefsCookiePersistor;
    }

    public static LoaderStrategy imageLoaderStrategy() {
        return strategy;
    }

    public static void setLoaderStrategy(LoaderStrategy strategy) {
        App.strategy = strategy;
    }
}
