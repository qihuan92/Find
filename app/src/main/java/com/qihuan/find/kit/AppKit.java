package com.qihuan.find.kit;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 工具类
 * Created by Qi on 2017/6/22.
 */

public class AppKit {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static long lastClickTime;

    public static void init(Context context) {
        AppKit.context = context;
    }

    public static Context getContext() {
        if (context == null) {
            throw new NullPointerException("you should init first");
        }
        return context;
    }

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
