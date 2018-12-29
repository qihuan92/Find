package module

import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.discovermodule.R
import com.qihuan.discovermodule.view.DiscoverFragment

/**
 * DiscoverMainActivity
 *
 * @author Qi
 */
class DiscoverMainActivity : BaseActivity() {

    private val content = DiscoverFragment.newInstance()

    override fun layoutResId(): Int {
        return R.layout.activity_main_discover
    }

    override fun init() {
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, content)
                .commit()
    }
}
