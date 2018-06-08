package com.qihuan.commonmodule.base

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
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
    protected lateinit var mStatusBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStatusBar = ImmersionBar.with(this)
        mStatusBar.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mStatusBar.destroy()
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
