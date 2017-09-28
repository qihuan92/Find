package com.qihuan.find;

import com.qihuan.find.di.component.DaggerAppComponent;
import com.qihuan.imageloader.LoaderStrategy;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * App
 * Created by Qi on 2017/6/20.
 */

public class App extends DaggerApplication {

    private static LoaderStrategy strategy;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public static LoaderStrategy imageLoaderStrategy() {
        return strategy;
    }

    public static void setLoaderStrategy(LoaderStrategy strategy) {
        App.strategy = strategy;
    }
}
