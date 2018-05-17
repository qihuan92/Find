package com.qihuan.moviemodule.model;

import com.qihuan.commonmodule.net.Api;
import com.qihuan.moviemodule.model.bean.CastsBean;
import com.qihuan.moviemodule.model.bean.MoviesBean;
import com.qihuan.moviemodule.model.bean.SubjectBean;
import com.qihuan.moviemodule.model.bean.USboxBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 豆瓣电影
 * Created by Qi on 2017/6/20.
 */

public interface DoubanApi {

    @Headers({Api.DOUBAN_DOMAIN})
    @GET("/v2/movie/top250")
    Observable<MoviesBean> getTopMovie(@Query("start") int start,
                                       @Query("count") int count);

    @Headers({Api.DOUBAN_DOMAIN})
    @GET("/v2/movie/us_box")
    Observable<USboxBean> getUSBox();

    @Headers({Api.DOUBAN_DOMAIN})
    @GET("/v2/movie/subject/{id}")
    Observable<SubjectBean> getSubject(@Path("id") String id);

    @Headers({Api.DOUBAN_DOMAIN})
    @GET("/v2/movie/celebrity/{id}")
    Observable<CastsBean> getCastDetail(@Path("id") String id);

    @Headers({Api.DOUBAN_DOMAIN})
    @GET("/v2/movie/search")
    Observable<MoviesBean> search(@Query("q") String q,
                                  @Query("start") int start,
                                  @Query("count") int count);
}
