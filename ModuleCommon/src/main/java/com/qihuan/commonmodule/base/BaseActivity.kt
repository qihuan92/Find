package com.qihuan.commonmodule.base

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.qihuan.commonmodule.R
import com.qihuan.commonmodule.bus.BindEventBus
import com.qihuan.commonmodule.views.LoadingDialog
import com.qihuan.commonmodule.views.TitleBar
import org.greenrobot.eventbus.EventBus

/**
 * BaseActivity
 *
 * @author Qi
 * @date 2017/6/20
 */

abstract class BaseActivity : AppCompatActivity(), LoadingDialogManager {

    override val loadingDialog: LoadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        // 取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // 设置状态栏颜色
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog.dismiss()
    }

    override fun onStart() {
        super.onStart()
        if (this.javaClass.isAnnotationPresent(BindEventBus::class.java)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (this.javaClass.isAnnotationPresent(BindEventBus::class.java)) {
            EventBus.getDefault().unregister(this)
        }
    }

    protected fun setToolBar(toolbar: TitleBar, title: String = "", @ColorInt color: Int = Color.parseColor("#00000000")) {
        toolbar.setOnBackClickListener { onBackPressed() }
        toolbar.setTitleText(title)
        toolbar.setBackgroundColor(color)
    }
}
