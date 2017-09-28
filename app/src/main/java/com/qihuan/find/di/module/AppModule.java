package com.qihuan.find.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * AppModule
 * Created by Qi on 2017/9/26.
 */

@Module(includes = {
        ActivityModule.class,
        FragmentModule.class,
        ViewModelModule.class,
        NetworkModule.class
})
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);
}
