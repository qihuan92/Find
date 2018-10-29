package com.qihuan.find.view

import android.content.Intent
import com.qihuan.commonmodule.base.BaseActivity

class LaunchActivity : BaseActivity() {

    override fun layoutResId(): Int {
        return 0
    }

    override fun init() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
