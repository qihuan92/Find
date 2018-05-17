package com.qihuan.commonmodule.base;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qihuan.commonmodule.R;
import com.qihuan.commonmodule.bus.BindEventBus;

import org.greenrobot.eventbus.EventBus;

/**
 * BaseActivity
 *
 * @author Qi
 * @date 2017/6/20
 */

public abstract class BaseActivity extends AppCompatActivity {

    private AlertDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
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


    public void showProgressLoading() {
        if (progressDialog == null) {
            progressDialog = new AlertDialog.Builder(this, R.style.ProgressDialog).create();
        }
        View loadView = getLayoutInflater().inflate(R.layout.alert_progress, null);
        progressDialog.setView(loadView, 0, 0, 0, 0);
        progressDialog.setCanceledOnTouchOutside(false);
        if (progressDialog.isShowing()) {
            return;
        }
        progressDialog.show();
    }

    public void hideProgressLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
