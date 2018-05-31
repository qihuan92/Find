package com.qihuan.commonmodule.base

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.qihuan.commonmodule.R
import com.qihuan.commonmodule.bus.BindEventBus
import org.greenrobot.eventbus.EventBus

/**
 * BaseActivity
 *
 * @author Qi
 * @date 2017/6/20
 */

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: AlertDialog? = null

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

    protected fun setToolBar(toolbar: Toolbar, title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        val bar = supportActionBar
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true)
            bar.setDisplayShowHomeEnabled(true)
        }
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    fun showProgressLoading() {
        if (progressDialog == null) {
            progressDialog = AlertDialog.Builder(this, R.style.ProgressDialog).create()
        }
        val loadView = layoutInflater.inflate(R.layout.alert_progress, null)
        progressDialog!!.setView(loadView, 0, 0, 0, 0)
        progressDialog!!.setCanceledOnTouchOutside(false)
        if (progressDialog!!.isShowing) {
            return
        }
        progressDialog!!.show()
    }

    fun hideProgressLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }
}
