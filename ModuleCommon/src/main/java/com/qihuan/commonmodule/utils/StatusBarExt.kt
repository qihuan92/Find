package com.qihuan.commonmodule.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * 状态栏工具
 *
 * @author Qi
 */
object StatusBarUtils {

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏文字及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    fun flymeSetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
                darkFlag.setAccessible(true)
                meizuFlags.setAccessible(true)
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                if (dark) {
                    value = value or bit
                } else {
                    value = value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return result
    }

    /**
     * 需要MIUIV6以上
     *
     * @param activity activity
     * @param dark     是否把状态栏文字及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    fun miuiSetStatusBarLightMode(activity: Activity, dark: Boolean): Boolean {
        var result = false
        val window = activity.window
        if (window != null) {
            val clazz = window.javaClass
            try {
                val darkModeFlag: Int
                @SuppressLint("PrivateApi")
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                if (dark) {
                    // 状态栏透明且黑色字体
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
                } else {
                    // 清除黑色字体
                    extraFlagField.invoke(window, 0, darkModeFlag)
                }
                result = true

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // 开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if (dark) {
                        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return result
    }

}

/**
 * 状态栏亮色模式，设置状态栏黑色文字、图标，
 * 适配4.4以上版本MIUI、Flyme和6.0以上版本其他Android
 *
 * @param activity activity
 * @return 1:MIUI 2:Flyme 3:android6.0
 */
fun Activity.statusBarLightMode() {
    if (DeviceUtils.isMiui) {
        StatusBarUtils.miuiSetStatusBarLightMode(this, true)
        return
    }
    if (DeviceUtils.isFlyme) {
        StatusBarUtils.flymeSetStatusBarLightMode(window, true)
        return
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

/**
 * 状态栏暗色模式，清除MIUI、flyme或6.0以上版本状态栏黑色文字、图标
 */
fun Activity.statusBarDarkMode(activity: Activity) {
    if (DeviceUtils.isMiui) {
        StatusBarUtils.miuiSetStatusBarLightMode(this, false)
        return
    }
    if (DeviceUtils.isFlyme) {
        StatusBarUtils.flymeSetStatusBarLightMode(window, false)
        return
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}
