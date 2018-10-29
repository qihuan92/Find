package module

import android.os.Bundle
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.view.DailyFragment

/**
 * DailyMainActivity
 *
 * @author Qi
 */
class DailyMainActivity : BaseActivity() {

    private val content = DailyFragment()

    override fun layoutResId(): Int {
        return R.layout.activity_main_daily
    }

    override fun init() {
        initView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_daily)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, content)
                .commit()
    }
}
