package com.qihuan.find.kit;

import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * ToastKit
 * Created by Qi on 2017/6/22.
 */

public class ToastKit {

    public static void info(String content) {
        Toasty.info(AppKit.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void error(String content) {
        Toasty.error(AppKit.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void success(String content) {
        Toasty.success(AppKit.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void warning(String content) {
        Toasty.warning(AppKit.getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
