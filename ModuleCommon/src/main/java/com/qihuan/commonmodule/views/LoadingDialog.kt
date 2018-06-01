package com.qihuan.commonmodule.views

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.qihuan.commonmodule.R

/**
 * LoadingDialog
 * @author qi
 * @date 2018/6/1
 */
class LoadingDialog(context: Context) : AlertDialog(context, R.style.ProgressDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_progress)
    }

    override fun show() {
        if (isShowing) {
            return
        }
        super.show()
    }

    init {
        setCanceledOnTouchOutside(false)
    }
}