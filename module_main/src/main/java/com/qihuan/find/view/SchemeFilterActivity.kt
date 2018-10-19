package com.qihuan.find.view

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter

class SchemeFilterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        直接通过ARouter处理外部Uri
        val uri = intent.data ?: return
        ARouter.getInstance().build(uri).navigation(this, object : NavCallback() {
            override fun onArrival(postcard: Postcard) {
                finish()
            }
        })
    }
}