package com.qihuan.find.view.base;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * BaseActivity
 * Created by Qi on 2017/6/20.
 */

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}
