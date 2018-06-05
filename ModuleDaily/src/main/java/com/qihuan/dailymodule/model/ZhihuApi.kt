package com.qihuan.dailymodule.model

import com.qihuan.commonmodule.net.Api
import com.qihuan.commonmodule.net.ApiManager
import com.qihuan.dailymodule.model.bean.CommentsBean
import com.qihuan.dailymodule.model.bean.DailyBean
import com.qihuan.dailymodule.model.bean.StoryContentBean
import com.qihuan.dailymodule.model.bean.StoryExtraBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * 知乎日报
 *
 * @author Qi
 * @date 2017/6/20
 */
interface ZhihuApi {

    companion object {
        fun get(): ZhihuApi {
            return ApiManager.instance.getApi(ZhihuApi::class.java)
        }
    }

    /**
     * 获取最新文章列表
     *
     * @return DailyBean
     */
    @Headers(Api.ZHIHU_DOMAIN)
    @GET("/api/4/news/latest")
    fun getLatestDaily(): Observable<DailyBean>

    /**
     * 获取以前的文章列表
     *
     * @return DailyBean
     */
    @Headers(Api.ZHIHU_DOMAIN)
    @GET("/api/4/news/before/{date}")
    fun getBeforeDaily(@Path("date") date: String): Observable<DailyBean>

    /**
     * 获取相应文章内容
     *
     * @param storyId storyId
     * @return StoryContentBean
     */
    @Headers(Api.ZHIHU_DOMAIN)
    @GET("/api/4/news/{storyId}")
    fun getStoryContent(@Path("storyId") storyId: Int): Observable<StoryContentBean>

    /**
     * 获取相应文章的额外信息，如评论数量，获得的赞等
     *
     * @param storyId storyId
     * @return StoryExtraBean
     */
    @Headers(Api.ZHIHU_DOMAIN)
    @GET("/api/4/story-extra/{storyId}")
    fun getStoryExtra(@Path("storyId") storyId: Int): Observable<StoryExtraBean>

    /**
     * 获取文章长评论
     *
     * @param storyId storyId
     * @return List<CommentsBean>
    </CommentsBean> */
    @Headers(Api.ZHIHU_DOMAIN)
    @GET("/api/4/story/{storyId}/long-comments")
    fun getLongComments(@Path("storyId") storyId: Int): Observable<List<CommentsBean>>

    /**
     * 获取文章短评论
     *
     * @param storyId storyId
     * @return List<CommentsBean>
    </CommentsBean> */
    @Headers(Api.ZHIHU_DOMAIN)
    @GET("/api/4/story/{storyId}/short-comments")
    fun getShortComments(@Path("storyId") storyId: Int): Observable<List<CommentsBean>>

}
