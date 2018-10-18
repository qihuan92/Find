package module

import android.os.Bundle

import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.view.MovieFragment

/**
 * MovieMainActivity
 *
 * @author Qi
 */
class MovieMainActivity : BaseActivity() {

    private val content = MovieFragment()

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
