package com.qihuan.find.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qihuan.find.kit.AppKit;
import com.qihuan.find.kit.LogKit;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.themeloader.Colorful;

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
                .doOnNext(Colorful::init)
                .doOnNext(ARouter::init)
                .doOnNext(LogKit::init)
                .doOnNext(ToastKit::init)
                .subscribe(
                        application -> startActivity(new Intent(application, MainActivity.class)),
                        e -> finish(),
                        this::finish
                );
    }

}
