package com.qihuan.find.di.module;

import com.qihuan.find.view.DailyDetActivity;
import com.qihuan.find.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * ActivityModule
 * Created by Qi on 2017/9/27.
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract DailyDetActivity dailyDetActivity();
}
