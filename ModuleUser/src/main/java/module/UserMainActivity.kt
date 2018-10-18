package module

import android.os.Bundle
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.usermodule.R
import com.qihuan.usermodule.view.MeFragment

/**
 * UserMainActivity
 *
 * @author Qi
 */
class UserMainActivity : BaseActivity() {

    private val content = MeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, content)
                .commit()
    }
}
