package com.qihuan.find.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.commonmodule.bus.BindEventBus
import com.qihuan.commonmodule.bus.event.BrowserEvent
import com.qihuan.commonmodule.bus.event.RefreshEvent
import com.qihuan.commonmodule.utils.setItemReselectedListener
import com.qihuan.commonmodule.utils.toastInfo
import com.qihuan.find.R
import com.qihuan.find.view.adapter.MainPageAdapter
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * MainActivity
 *
 * @author Qi
 */
@BindEventBus
class MainActivity : BaseActivity() {

    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStatusBar.statusBarDarkFont(true, 0.2f).init()
        initView()
    }

    private fun initView() {
        val mainPageAdapter = MainPageAdapter(supportFragmentManager)
        lifecycle.addObserver(mainPageAdapter)
        vp_content.adapter = mainPageAdapter
        tb_main.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(vp_content))
        vp_content.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tb_main))

        // 首页刷新
        tb_main.setItemReselectedListener(0) {
            EventBus.getDefault().post(RefreshEvent())
        }
    }

    @Subscribe
    fun onBrowserEvent(browserEvent: BrowserEvent?) {
        browserEvent?.run {
            val webBuilder = FinestWebView.Builder(applicationContext)
            if (title.isNotBlank()) {
                webBuilder.titleDefault(title)
            }
            if (url.isNotBlank()) {
                webBuilder.show(url)
            }
        }
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
