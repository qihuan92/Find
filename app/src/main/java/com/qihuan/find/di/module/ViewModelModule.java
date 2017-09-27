package com.qihuan.find.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.qihuan.find.di.annotation.ViewModelKey;
import com.qihuan.find.viewmodel.DailyDetViewModel;
import com.qihuan.find.viewmodel.DailyViewModel;
import com.qihuan.find.viewmodel.factory.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * ViewModelModule
 * Created by Qi on 2017/9/27.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DailyViewModel.class)
    abstract ViewModel bindDailyViewModel(DailyViewModel dailyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DailyDetViewModel.class)
    abstract ViewModel bindDailyDetViewModel(DailyDetViewModel dailyDetViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
