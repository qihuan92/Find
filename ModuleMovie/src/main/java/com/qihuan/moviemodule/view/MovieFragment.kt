package com.qihuan.moviemodule.view


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpFragment
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.dp2px
import com.qihuan.commonmodule.utils.inflate
import com.qihuan.commonmodule.utils.toastInfo
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieContract
import com.qihuan.moviemodule.model.bean.MovieSectionItemBean
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.presenter.MoviePresenter
import com.qihuan.moviemodule.view.adapter.MovieCardAdapter
import com.qihuan.moviemodule.view.adapter.MovieSelectionAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Routes.MOVIE_FRAGMENT)
class MovieFragment : BaseMvpFragment<MovieContract.View, MovieContract.Presenter>(), MovieContract.View {

    private lateinit var movieAdapter: MovieSelectionAdapter
    private lateinit var movieCardAdapter: MovieCardAdapter
    private var tvInTheatersTitle: TextView? = null
    private var tvInTheatersMore: TextView? = null

    override fun initPresenter(): MovieContract.Presenter {
        return MoviePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieCardAdapter = MovieCardAdapter()
        movieAdapter = MovieSelectionAdapter()
        rv_list.layoutManager = LinearLayoutManager(context)

        // 上映
        context?.apply {
            // title
            val inTheatersTitleView: View = inflate(R.layout.item_section_movie, rv_list)
            tvInTheatersTitle = inTheatersTitleView.findViewById(R.id.tv_title)
            tvInTheatersMore = inTheatersTitleView.findViewById(R.id.tv_more)
            tvInTheatersMore?.setOnClickListener {

            }
            movieAdapter.addHeaderView(inTheatersTitleView)
            // content
            val rvInTheaters = RecyclerView(this)
            rvInTheaters.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            rvInTheaters.setPadding(dp2px(6f), 0, dp2px(6f), 0)
            rvInTheaters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            rvInTheaters.adapter = movieCardAdapter
            movieCardAdapter.setOnItemClickListener { adapter, _, position ->
                val itemBean = adapter.data[position] as SubjectBean
                start(itemBean.id)
            }
            movieAdapter.addHeaderView(rvInTheaters)
        }

        rv_list.adapter = movieAdapter
        refresh_layout.setOnRefreshListener { mPresenter.getMovieData() }
        refresh_layout.autoRefresh()

        movieAdapter.setOnItemClickListener { adapter, _, position ->
            val itemBean = adapter.data[position] as MovieSectionItemBean
            if (itemBean.isHeader) {
                return@setOnItemClickListener
            }
            start(itemBean.t.id)
        }

        movieAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.tv_more) {
                context?.toastInfo("标题 $position")
            }
        }
    }

    override fun onData(inTheaters: MoviesBean, sectionItemList: List<MovieSectionItemBean>) {
        refresh_layout.finishRefresh(true)
        tvInTheatersTitle?.text = inTheaters.title
        movieCardAdapter.setNewData(inTheaters.subjects)
        movieAdapter.setNewData(sectionItemList)
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        refresh_layout.finishRefresh(false)
    }

    fun start(id: String) {
        ARouter.getInstance()
                .build(Routes.MOVIE_DET_ACTIVITY)
                .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, id)
                .navigation()
    }
}
