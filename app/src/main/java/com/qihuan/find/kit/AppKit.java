package com.qihuan.find.kit;

/**
 * 工具类
 * Created by Qi on 2017/6/22.
 */

public class AppKit {
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
