package com.qihuan.find.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qihuan.find.App;
import com.qihuan.find.common.GlideStrategy;
import com.qihuan.find.kit.AppKit;
import com.qihuan.find.kit.ToastKit;

import io.reactivex.Observable;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        Observable.just(getApplication())
                .doOnNext(AppKit::init)
                .doOnNext(ARouter::init)
                .doOnNext(ToastKit::init)
                .doOnNext(application -> App.setLoaderStrategy(new GlideStrategy()))
                .doOnNext(application -> App.setSharedPrefsCookiePersistor(new SharedPrefsCookiePersistor(application)))
                .subscribe(
                        application -> startActivity(new Intent(application, MainActivity.class)),
                        e -> finish(),
                        this::finish
                );
    }

}
