package com.qihuan.find.di.module;

import com.qihuan.find.view.adapter.DailyAdapter;
import com.qihuan.imageloader.LoaderStrategy;

import dagger.Module;
import dagger.Provides;

/**
 * AdapterModule
 * Created by Qi on 2017/9/28.
 */

@Module
public class AdapterModule {

    @Provides
    DailyAdapter provideDailyAdapter(LoaderStrategy loaderStrategy) {
        return new DailyAdapter(loaderStrategy);
    }
}
