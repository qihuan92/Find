package com.qihuan.find.view

import android.content.Intent
import android.os.Bundle
import com.qihuan.commonmodule.base.BaseActivity

class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
