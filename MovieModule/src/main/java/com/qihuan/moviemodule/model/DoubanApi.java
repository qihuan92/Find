package com.qihuan.moviemodule.model;

import com.qihuan.moviemodule.model.bean.CastsBean;
import com.qihuan.moviemodule.model.bean.MoviesBean;
import com.qihuan.moviemodule.model.bean.SubjectBean;
import com.qihuan.moviemodule.model.bean.USboxBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 豆瓣电影
 * Created by Qi on 2017/6/20.
 */

public interface DoubanApi {
    String DOUBAN_URL = "https://api.douban.com/v2";

    @GET("movie/top250")
    Flowable<MoviesBean> getTopMovie(@Query("start") int start,
                                     @Query("count") int count);

    @GET("movie/us_box")
    Flowable<USboxBean> getUSBox();

    @GET("movie/subject/{id}")
    Flowable<SubjectBean> getSubject(@Path("id") String id);

    @GET("movie/celebrity/{id}")
    Flowable<CastsBean> getCastDetail(@Path("id") String id);

    @GET("movie/search")
    Flowable<MoviesBean> search(@Query("q") String q,
                                  @Query("start") int start,
                                  @Query("count") int count);
}
