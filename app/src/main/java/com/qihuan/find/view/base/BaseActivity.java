package com.qihuan.find.view.base;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.qihuan.find.kit.LogKit;

import org.polaric.colorful.Colorful;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * BaseActivity
 * Created by Qi on 2017/6/20.
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private String themeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get theme
        this.themeString = Colorful.getThemeString();
        this.setTheme(Colorful.getThemeDelegate().getStyleResBase());
        this.getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResPrimary(), true);
        this.getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResAccent(), true);
        if (Build.VERSION.SDK_INT >= 21) {
            if (Colorful.getThemeDelegate().isTranslucent()) {
                this.getWindow().addFlags(67108864);
            }
            ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(null, null, this.getResources().getColor(Colorful.getThemeDelegate().getPrimaryColor().getColorRes()));
            this.setTaskDescription(tDesc);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Colorful.getThemeString().equals(this.themeString)) {
            LogKit.d("Theme change detected, restarting activity");
            this.recreate();
        }

    }

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
