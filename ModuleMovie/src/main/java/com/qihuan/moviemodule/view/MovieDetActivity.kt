package com.qihuan.moviemodule.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpActivity
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.appendTextList
import com.qihuan.commonmodule.utils.getColor
import com.qihuan.commonmodule.utils.load
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieDetContract
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.presenter.MovieDetPresenter
import kotlinx.android.synthetic.main.activity_movie_det.*
import kotlinx.android.synthetic.main.include_movie_title.*

/**
 * MovieDetActivity
 * @author qi
 * @date 2018/6/6
 */
@Route(path = Routes.MOVIE_DET_ACTIVITY)
class MovieDetActivity : BaseMvpActivity<MovieDetContract.View, MovieDetContract.Presenter>(), MovieDetContract.View {

    @JvmField
    @Autowired
    var id: String = ""

    override fun initPresenter(): MovieDetContract.Presenter {
        return MovieDetPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_det)
        ARouter.getInstance().inject(this)
        initView()
        mPresenter.getSubject(id)
    }

    @SuppressLint("SetTextI18n")
    override fun onSubject(subjectBean: SubjectBean) {

        subjectBean.apply {
            // 标题信息
            tv_title.text = title
            tv_genres.text = "电影类型："
            tv_genres.appendTextList(genres)
            tv_aka.text = "原名：${aka[0]}"
            tv_year.text = "上映时间：${year}年"
            // 评分
            tv_score_number.text = rating.average.toString()
            tv_score_count.text = "${ratings_count}人评价"
            rb_score.rating = (rating.average / 2f).toFloat()
            // 电影海报
            iv_movie.load(images.large, 4f, listener = {
                it?.apply {
                    ctl_movie.setContentScrimColor(getColor())
                    ctl_movie.setBackgroundColor(getColor())
                    tv_title.setTextColor(getColor())
                }
            })
            // 简介
            tv_summary.text = summary
        }
    }

    private fun initView() {
        setToolBar(toolbar)
    }
}