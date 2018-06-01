package com.qihuan.find.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.commonmodule.bus.BindEventBus
import com.qihuan.commonmodule.bus.event.BrowserEvent
import com.qihuan.commonmodule.bus.event.RefreshEvent
import com.qihuan.commonmodule.router.Router
import com.qihuan.commonmodule.utils.disableShiftMode
import com.qihuan.commonmodule.utils.statusBarLightMode
import com.qihuan.commonmodule.utils.toastInfo
import com.qihuan.find.R
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
    private var content = ARouter.getInstance().build(Router.DAILY_FRAGMENT).navigation() as Fragment
    private val dailyFragment = ARouter.getInstance().build(Router.DAILY_FRAGMENT).navigation() as Fragment
    private val movieFragment = ARouter.getInstance().build(Router.MOVIE_FRAGMENT).navigation() as Fragment
    private val discoverFragment = ARouter.getInstance().build(Router.DISCOVER_FRAGMENT).navigation() as Fragment
    private val meFragment = ARouter.getInstance().build(Router.USER_FRAGMENT).navigation() as Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        // super.onSaveInstanceState(outState);
    }

    private fun initView() {
        // 状态栏白色主题
        statusBarLightMode()

        bottom_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bb_menu_daily -> switchContent(dailyFragment)
                R.id.bb_menu_movie -> switchContent(movieFragment)
                R.id.bb_menu_discover -> switchContent(discoverFragment)
                R.id.bb_menu_me -> switchContent(meFragment)
                else -> {
                }
            }
            true
        }

        bottom_view.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.bb_menu_daily -> EventBus.getDefault().postSticky(RefreshEvent())
                R.id.bb_menu_movie -> {
                }
                R.id.bb_menu_discover -> {
                }
                R.id.bb_menu_me -> {
                }
                else -> {
                }
            }
        }

        bottom_view.disableShiftMode()

        switchContent(dailyFragment)
    }

    private fun switchContent(fragment: Fragment) {
        if (content !== fragment) {
            if (fragment.isAdded) {
                supportFragmentManager.beginTransaction().hide(content).show(fragment).commit()
            } else {
                supportFragmentManager.beginTransaction().hide(content).add(R.id.fl_content, fragment).commit()
            }
        }
        content = fragment
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
