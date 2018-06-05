package com.qihuan.moviemodule.model

import com.qihuan.commonmodule.net.Api
import com.qihuan.commonmodule.net.ApiManager
import com.qihuan.moviemodule.model.bean.CastsBean
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.model.bean.USboxBean

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 豆瓣电影
 * Created by Qi on 2017/6/20.
 */

interface DoubanApi {

    companion object {
        fun get(): DoubanApi {
            return ApiManager.instance.getApi(DoubanApi::class.java)
        }
    }

    @Headers(Api.DOUBAN_DOMAIN)
    @GET("/v2/movie/us_box")
    fun getUsBox(): Observable<USboxBean>

    @Headers(Api.DOUBAN_DOMAIN)
    @GET("/v2/movie/top250")
    fun getTopMovie(@Query("start") start: Int = 0, @Query("count") count: Int = 10): Observable<MoviesBean>

    /**
     * 获取上映电影
     */
    @Headers(Api.DOUBAN_DOMAIN)
    @GET("/v2/movie/in_theaters")
    fun getInTheaters(@Query("city") city: String = "北京", @Query("start") start: Int = 0, @Query("count") count: Int = 10): Observable<MoviesBean>

    /**
     * 电影详情
     */
    @Headers(Api.DOUBAN_DOMAIN)
    @GET("/v2/movie/subject/{id}")
    fun getSubject(@Path("id") id: String): Observable<SubjectBean>

    /**
     * 查询影人
     */
    @Headers(Api.DOUBAN_DOMAIN)
    @GET("/v2/movie/celebrity/{id}")
    fun getCastDetail(@Path("id") id: String): Observable<CastsBean>

    @Headers(Api.DOUBAN_DOMAIN)
    @GET("/v2/movie/search")
    fun search(@Query("q") q: String, @Query("start") start: Int = 0, @Query("count") count: Int = 10): Observable<MoviesBean>
}
