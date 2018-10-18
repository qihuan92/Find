package module

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_discover)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, content)
                .commit()
    }
}
