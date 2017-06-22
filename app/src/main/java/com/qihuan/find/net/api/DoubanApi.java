package com.qihuan.find.net.api;

import com.qihuan.find.bean.douban.CastsBean;
import com.qihuan.find.bean.douban.MoviesBean;
import com.qihuan.find.bean.douban.SubjectBean;
import com.qihuan.find.bean.douban.USboxBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 豆瓣电影
 * Created by Qi on 2017/6/20.
 */

public interface DoubanApi {
    @GET("movie/top250")
    Observable<MoviesBean> getTopMovie(@Query("start") int start,
                                       @Query("count") int count);

    @GET("movie/us_box")
    Observable<USboxBean> getUSBox();

    @GET("movie/subject/{id}")
    Observable<SubjectBean> getSubject(@Path("id") String id);

    @GET("movie/celebrity/{id}")
    Observable<CastsBean> getCastDetail(@Path("id") String id);

    @GET("movie/search")
    Observable<MoviesBean> search(@Query("q") String q,
                                  @Query("start") int start,
                                  @Query("count") int count);
}
