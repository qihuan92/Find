package com.qihuan.moviemodule.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpFragment
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.commonmodule.utils.toastInfo
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieContract
import com.qihuan.moviemodule.model.bean.MovieHomeBean
import com.qihuan.moviemodule.presenter.MoviePresenter
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
class MovieFragment : BaseMvpFragment<MovieContract.View, MovieContract.Presenter>(), MovieContract.View {

    override fun initPresenter(): MovieContract.Presenter {
        return MoviePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh_layout.setOnRefreshListener { mPresenter.getMovieData() }
        refresh_layout.autoRefresh()
    }

    @SuppressLint("SetTextI18n")
    override fun onData(homeBean: MovieHomeBean) {
        refresh_layout.finishRefresh(true)
        rv_list.linear {
            // 上映
            homeBean.inTheaters.apply {
                // item list
                itemDsl {
                    xml(R.layout.item_section_movie)
                    render {
                        it.tv_section_title.text = title
                        it.tv_section_more.setOnClickListener {
                            toastInfo("$title more")
                        }
                    }
                }
                // 上映横向列表
                itemDsl {
                    xml(R.layout.layout_list)
                    render {
                        it.rv_list.linear {
                            orientation(LinearLayoutManager.HORIZONTAL)
                            subjects?.forEach { subject ->
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
                            toastInfo("$title more")
                        }
                    }
                }
                // item list
                subjects?.forEachIndexed { index, subject ->
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
                        it.tv_section_more.setOnClickListener {
                            toastInfo("$title more")
                        }
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

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        refresh_layout.finishRefresh(false)
    }

    private fun start(id: String) {
        ARouter.getInstance()
                .build(Routes.MOVIE_DET_ACTIVITY)
                .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, id)
                .navigation()
    }
}