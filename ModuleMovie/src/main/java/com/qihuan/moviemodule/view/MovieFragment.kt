package com.qihuan.moviemodule.view


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseMvpFragment
import com.qihuan.commonmodule.router.Router
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieContract
import com.qihuan.moviemodule.model.bean.MovieHomeBean
import com.qihuan.moviemodule.presenter.MoviePresenter
import com.qihuan.moviemodule.view.adapter.MovieCardAdapter
import com.qihuan.moviemodule.view.adapter.MovieRankingAdapter
import com.qihuan.moviemodule.view.adapter.UsBoxMovieRankingAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Router.MOVIE_FRAGMENT)
class MovieFragment : BaseMvpFragment<MovieContract.View, MovieContract.Presenter>(), MovieContract.View {

    private lateinit var movieCardAdapter: MovieCardAdapter
    private lateinit var topMovieAdapter: MovieRankingAdapter
    private lateinit var usBoxAdapter: UsBoxMovieRankingAdapter

    override fun initPresenter(): MovieContract.Presenter {
        return MoviePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 热映
        movieCardAdapter = MovieCardAdapter()
        rv_in_theaters.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_in_theaters.adapter = movieCardAdapter
        // top 250
        topMovieAdapter = MovieRankingAdapter()
        rv_top_movie.layoutManager = LinearLayoutManager(context)
        rv_top_movie.adapter = topMovieAdapter
        rv_top_movie.setHasFixedSize(true)
        rv_top_movie.isNestedScrollingEnabled = false
        // us box
        usBoxAdapter = UsBoxMovieRankingAdapter()
        rv_us_box.layoutManager = LinearLayoutManager(context)
        rv_us_box.adapter = usBoxAdapter
        rv_us_box.setHasFixedSize(true)
        rv_us_box.isNestedScrollingEnabled = false

        mPresenter.getMovieData()
    }

    override fun onData(movieHomeBean: MovieHomeBean) {
        movieHomeBean.apply {
            tv_in_theaters.text = inTheaters.title
            movieCardAdapter.setNewData(inTheaters.subjects)

            tv_top_movie.text = topMovie.title
            topMovieAdapter.setNewData(topMovie.subjects)

            tv_us_box.text = usBox.title
            usBoxAdapter.setNewData(usBox.subjects)
        }
    }

}
