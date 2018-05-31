package module

import android.os.Bundle
import android.support.v4.app.Fragment

import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.view.MovieFragment

/**
 * MainActivity
 *
 * @author Qi
 */
class MainActivity : BaseActivity() {

    private val content = MovieFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, content)
                .commit()
    }
}
