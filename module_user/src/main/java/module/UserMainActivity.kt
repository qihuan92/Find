package module

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

    override fun layoutResId(): Int {
        return R.layout.activity_main_user
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
