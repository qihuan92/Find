package com.qihuan.commonmodule.utils;

import android.app.Application;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.qihuan.commonmodule.R;

import es.dmoral.toasty.Toasty;

/**
 * ToastUtils
 * Created by Qi on 2017/6/22.
 */

public class ToastUtils {

    public static void info(String content) {
        Toasty.info(AppUtils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void error(String content) {
        Toasty.error(AppUtils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void success(String content) {
        Toasty.success(AppUtils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void warning(String content) {
        Toasty.warning(AppUtils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void init(Application application) {
        Toasty.Config
                .getInstance()
                .setInfoColor(ContextCompat.getColor(application, R.color.grey))
                .apply();
    }
}
