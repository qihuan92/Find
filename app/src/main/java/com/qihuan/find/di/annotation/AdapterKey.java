package com.qihuan.find.di.annotation;

import android.support.v7.widget.RecyclerView;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * AdapterKey
 * Created by Qi on 2017/9/28.
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface AdapterKey {
    Class<? extends RecyclerView.Adapter> value();
}
