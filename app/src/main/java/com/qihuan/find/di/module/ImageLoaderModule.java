package com.qihuan.find.di.module;

import com.qihuan.find.common.GlideStrategy;
import com.qihuan.imageloader.LoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ImageLoaderModule
 * Created by Qi on 2017/9/28.
 */

@Module
public class ImageLoaderModule {

    @Singleton
    @Provides
    LoaderStrategy provideLoaderStrategy() {
        return new GlideStrategy();
    }
}
