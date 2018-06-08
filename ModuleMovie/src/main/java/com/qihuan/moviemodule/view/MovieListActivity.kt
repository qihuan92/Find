package com.qihuan.moviemodule.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpActivity
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieListContract
import com.qihuan.moviemodule.model.bean.MovieListType
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.presenter.MovieListPresenter
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.item_movie.view.*
import zlc.season.yaksa.linear

/**
 * MovieDetActivity
 * @author qi
 * @date 2018/6/6
 */
@Route(path = Routes.MOVIE_LIST_ACTIVITY)
class MovieListActivity : BaseMvpActivity<MovieListContract.View, MovieListContract.Presenter>(), MovieListContract.View {

    @JvmField
    @Autowired(name = Routes.MOVIE_LIST_ACTIVITY_EXTRA_TYPE)
    var type: Int = MovieListType.IN_THEATERS

    @JvmField
    @Autowired(name = Routes.MOVIE_LIST_ACTIVITY_EXTRA_TITLE)
    var title: String = ""

    private var mSubjectList = ArrayList<SubjectBean>()

    override fun initPresenter(): MovieListContract.Presenter {
        return MovieListPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ARouter.getInstance().inject(this)
        initView()
    }

    private fun initView() {
        mStatusBar.statusBarColor(R.color.colorPrimaryDark).init()
        setToolBar(toolbar, title)

        refresh_layout.setOnRefreshListener {
            mPresenter.clearData()
            getDataList()
        }
        refresh_layout.setOnLoadMoreListener {
            getDataList()
        }
        refresh_layout.autoRefresh()
    }

    @SuppressLint("SetTextI18n")
    override fun onSubjectList(isRefresh: Boolean, subjectList: List<SubjectBean>) {
        if (isRefresh) {
            mSubjectList.clear()
            refresh_layout.finishRefresh(true)
        } else {
            refresh_layout.finishLoadMore(true)
        }
        mSubjectList.addAll(subjectList)

        rv_list.linear {
            mSubjectList.forEach { subject ->
                itemDsl {
                    xml(R.layout.item_movie)
                    render {
                        subject.apply {
                            it.iv_item_movie.load(images.small, 4f)
                            it.tv_item_movie.text = title
                            it.tv_item_year.text = "[$year]"
                            it.tv_item_score.text = rating.average.toString()
                            it.rb_item_score.rating = (rating.average / 2f).toFloat()
                            it.tag_item_genres.tags = genres
                            it.tv_item_original_title.text = original_title
                            it.setOnClickListener {
                                start(id)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun showError(errorMsg: String) {
        mSubjectList.clear()
        refresh_layout.finishRefresh(false)
        refresh_layout.finishLoadMore(false)
    }

    private fun getDataList() {
        when (type) {
            MovieListType.IN_THEATERS -> mPresenter.getInTheaters()
            MovieListType.TOP_MOVIE -> mPresenter.getTopMovie()
        }
    }

    private fun start(id: String) {
        ARouter.getInstance()
                .build(Routes.MOVIE_DET_ACTIVITY)
                .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, id)
                .navigation()
    }
}