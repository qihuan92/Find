package com.qihuan.find.di.module;

import com.qihuan.find.view.DailyFragment;
import com.qihuan.find.view.DiscoverFragment;
import com.qihuan.find.view.MeFragment;
import com.qihuan.find.view.MovieFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * FragmentModule
 * Created by Qi on 2017/9/27.
 */

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract DailyFragment dailyFragment();

    @ContributesAndroidInjector
    abstract MovieFragment movieFragment();

    @ContributesAndroidInjector
    abstract DiscoverFragment discoverFragment();

    @ContributesAndroidInjector
    abstract MeFragment meFragment();
}
