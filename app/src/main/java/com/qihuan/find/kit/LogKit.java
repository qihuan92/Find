package com.qihuan.find.kit;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * LogKit
 * Created by Qi on 2017/9/28.
 */

public class LogKit {

    public static void init(Application application) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag(application.getPackageName())   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void v(String mes, Object... o) {
        Logger.v(mes, o);
    }

    public static void d(String mes, Object... o) {
        Logger.d(mes, o);
    }

    public static void d(Object... o) {
        Logger.d(o);
    }

    public static void i(String mes, Object... o) {
        Logger.i(mes, o);
    }

    public static void w(String mes, Object... o) {
        Logger.w(mes, o);
    }

    public static void e(String mes, Object... o) {
        Logger.e(mes, o);
    }

    public static void e(Throwable e, String mes, Object... o) {
        Logger.e(e, mes, o);
    }

    public static void wtf(String mes, Object... o) {
        Logger.wtf(mes, o);
    }

}
