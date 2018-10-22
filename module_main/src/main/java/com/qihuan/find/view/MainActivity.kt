package com.qihuan.find.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.tabs.TabLayout
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.commonmodule.bus.BrowserEvent
import com.qihuan.commonmodule.bus.EVENT_OPEN_BROWSER
import com.qihuan.commonmodule.bus.EVENT_REFRESH_DAILY
import com.qihuan.commonmodule.bus.LiveDataBus
import com.qihuan.commonmodule.utils.setItemReselectedListener
import com.qihuan.commonmodule.utils.toastInfo
import com.qihuan.find.R
import com.qihuan.find.view.adapter.MainPageAdapter
import com.qihuan.find.work.MainWorker
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

/**
 * MainActivity
 *
 * @author Qi
 */
class MainActivity : BaseActivity() {

    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStatusBar.statusBarDarkFont(true, 0.2f).init()
        initView()
        initWork()
    }

    private fun initWork() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val worker = PeriodicWorkRequest.Builder(MainWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance().enqueue(worker)
        WorkManager.getInstance().getStatusByIdLiveData(worker.id)
                .observe(this, Observer { workStatus ->
                    if (workStatus != null && workStatus.state.isFinished) {
                        Log.d("WorkManager", "WorkManager isFinished")
                    }
                })
    }

    private fun initView() {
        val mainPageAdapter = MainPageAdapter(supportFragmentManager)
        lifecycle.addObserver(mainPageAdapter)
        vp_content.adapter = mainPageAdapter
        tb_main.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(vp_content))
        vp_content.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tb_main))

        // 首页刷新
        tb_main.setItemReselectedListener(0) {
            LiveDataBus.get()
                    .with(EVENT_REFRESH_DAILY)
                    .postValue(null)
        }

        LiveDataBus.get()
                .with(EVENT_OPEN_BROWSER, BrowserEvent::class.java)
                .observe(this, Observer {
                    val webBuilder = FinestWebView.Builder(applicationContext)
                    if (title.isNotBlank()) {
                        webBuilder.titleDefault(it.title)
                    }
                    if (it.url.isNotBlank()) {
                        webBuilder.show(it.url)
                    }
                })
    }

    override fun onBackPressed() {
        if (!isFastClick()) {
            toastInfo(getString(R.string.double_click_exit))
            return
        }
        super.onBackPressed()
    }

    @Synchronized
    private fun isFastClick(): Boolean {
        val time = System.currentTimeMillis()
        if (time - lastClickTime < 1500) {
            return true
        }
        lastClickTime = time
        return false
    }

}
