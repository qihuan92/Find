package com.qihuan.commonmodule.base

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.qihuan.commonmodule.R
import com.qihuan.commonmodule.utils.parseColorRes
import com.qihuan.commonmodule.views.LoadingDialog
import com.qihuan.commonmodule.views.TitleBar

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
                .navigationBarColor(android.R.color.white)
        mStatusBar.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mStatusBar.destroy()
        loadingDialog.dismiss()
    }

    protected fun setToolBar(toolbar: TitleBar, title: String = "", @ColorRes color: Int = R.color.colorPrimary, theme: Int = TitleBar.DARK) {
        toolbar.setOnBackClickListener { onBackPressed() }
        toolbar.setTitleText(title)
        toolbar.setBackgroundColor(parseColorRes(color))
        toolbar.setTheme(theme)
    }
}
