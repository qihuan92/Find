package com.qihuan.find.view.base;

import android.annotation.SuppressLint;
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}
