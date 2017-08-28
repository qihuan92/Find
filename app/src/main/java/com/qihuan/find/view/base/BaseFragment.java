package com.qihuan.find.view.base;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * BaseFragment
 * Created by Qi on 2017/6/22.
 */

public class BaseFragment extends LifecycleFragment {

    private ProgressDialog progressDialog;
    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    public void showProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("加载中, 请稍后...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
