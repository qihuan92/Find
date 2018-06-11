package com.qihuan.moviemodule.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvvmFragment
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.commonmodule.utils.setVisible
import com.qihuan.commonmodule.utils.toastError
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.MovieHomeBean
import com.qihuan.moviemodule.model.bean.MovieListType
import com.qihuan.moviemodule.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.item_movie_card.view.*
import kotlinx.android.synthetic.main.item_movie_ranking.view.*
import kotlinx.android.synthetic.main.item_section_movie.view.*
import kotlinx.android.synthetic.main.layout_list.view.*
import zlc.season.yaksa.linear

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Routes.MOVIE_FRAGMENT)
class MovieFragment : BaseMvvmFragment<MovieViewModel>(MovieViewModel::class.java) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getMovieData()
        mViewModel.bindMovieData(this, ::onData)
        mViewModel.bindUIState(this) {
            when (it) {
                MovieViewModel.UIState.LOADING -> {
                }
                MovieViewModel.UIState.FINISH -> {
                }
                MovieViewModel.UIState.ERROR -> toastError()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onData(homeBean: MovieHomeBean?) {
        if (homeBean == null) {
            return
        }
        rv_list.linear {
            // 上映
            homeBean.inTheaters.apply {
                // item list
                itemDsl {
                    xml(R.layout.item_section_movie)
                    render {
                        it.tv_section_title.text = title
                        it.tv_section_more.setOnClickListener {
                            startList(title, MovieListType.IN_THEATERS)
                        }
                    }
                }
                // 上映横向列表
                itemDsl {
                    xml(R.layout.layout_list)
                    render {
                        it.rv_list.linear {
                            orientation(LinearLayoutManager.HORIZONTAL)
                            subjects.forEach { subject ->
                                itemDsl {
                                    xml(R.layout.item_movie_card)
                                    render {
                                        it.iv_card_movie.load(subject.images.medium)
                                        it.tv_card_movie.text = subject.title
                                        it.tv_card_score.text = subject.rating.average.toString()
                                        it.rb_card_score.rating = (subject.rating.average / 2f).toFloat()
                                        it.setOnClickListener {
                                            start(subject.id)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // top 250
            homeBean.topMovie.apply {
                // header
                itemDsl {
                    xml(R.layout.item_section_movie)
                    render {
                        it.tv_section_title.text = title
                        it.tv_section_more.setOnClickListener {
                            startList(title, MovieListType.TOP_MOVIE)
                        }
                    }
                }
                // item list
                subjects.forEachIndexed { index, subject ->
                    itemDsl {
                        xml(R.layout.item_movie_ranking)
                        render {
                            subject.apply {
                                it.tv_ranking.text = String.format("%02d", index + 1)
                                it.iv_ranking_movie.load(images.small, 4f)
                                it.tv_ranking_movie.text = title
                                it.tv_ranking_year.text = "[$year]"
                                it.tv_ranking_score.text = rating.average.toString()
                                it.rb_ranking_score.rating = (rating.average / 2f).toFloat()
                                it.tag_ranking_genres.tags = genres
                                it.setOnClickListener {
                                    start(id)
                                }
                            }
                        }
                    }
                }
            }

            // us box
            homeBean.usBox.apply {
                // header
                itemDsl {
                    xml(R.layout.item_section_movie)
                    render {
                        it.tv_section_title.text = title
                        it.tv_section_more.setVisible(false)
                    }
                }
                // item list
                subjects?.forEachIndexed { index, subjects ->
                    itemDsl {
                        xml(R.layout.item_movie_ranking)
                        render {
                            subjects.subject.apply {
                                it.tv_ranking.text = String.format("%02d", index + 1)
                                it.iv_ranking_movie.load(images.small, 4f)
                                it.tv_ranking_movie.text = title
                                it.tv_ranking_year.text = "[$year]"
                                it.tv_ranking_score.text = rating.average.toString()
                                it.rb_ranking_score.rating = (rating.average / 2f).toFloat()
                                it.tag_ranking_genres.tags = genres
                                it.setOnClickListener {
                                    start(id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun start(id: String) {
        ARouter.getInstance()
                .build(Routes.MOVIE_DET_ACTIVITY)
                .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, id)
                .navigation()
    }

    private fun startList(title: String, type: Int) {
        ARouter.getInstance()
                .build(Routes.MOVIE_LIST_ACTIVITY)
                .withInt(Routes.MOVIE_LIST_ACTIVITY_EXTRA_TYPE, type)
                .withString(Routes.MOVIE_LIST_ACTIVITY_EXTRA_TITLE, title)
                .navigation()
    }
}
