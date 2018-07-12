package module

import android.os.Bundle
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.view.DailyFragment

/**
 * MainActivity
 *
 * @author Qi
 */
class MainActivity : BaseActivity() {

    private val content = DailyFragment()

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
