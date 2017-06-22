package com.qihuan.find.kit;

import android.widget.Toast;

import com.blankj.utilcode.util.Utils;

import es.dmoral.toasty.Toasty;

/**
 * ToastKit
 * Created by Qi on 2017/6/22.
 */

public class ToastKit {

    public static void info(String content) {
        Toasty.info(Utils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void error(String content) {
        Toasty.error(Utils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void success(String content) {
        Toasty.success(Utils.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void warning(String content) {
        Toasty.warning(Utils.getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
