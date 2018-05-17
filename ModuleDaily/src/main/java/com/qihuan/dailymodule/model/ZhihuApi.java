package com.qihuan.dailymodule.model;

import com.qihuan.commonmodule.net.Api;
import com.qihuan.dailymodule.model.bean.CommentsBean;
import com.qihuan.dailymodule.model.bean.DailyBean;
import com.qihuan.dailymodule.model.bean.StoryContentBean;
import com.qihuan.dailymodule.model.bean.StoryExtraBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * 知乎日报
 *
 * @author Qi
 * @date 2017/6/20
 */
public interface ZhihuApi {

    /**
     * 获取最新文章列表
     *
     * @return DailyBean
     */
    @Headers({Api.ZHIHU_DOMAIN})
    @GET("/api/4/news/latest")
    Observable<DailyBean> getLatestDaily();

    /**
     * 获取以前的文章列表
     *
     * @return DailyBean
     */
    @Headers({Api.ZHIHU_DOMAIN})
    @GET("/api/4/news/before/{date}")
    Observable<DailyBean> getBeforeDaily(@Path("date") String date);

    /**
     * 获取相应文章内容
     *
     * @param storyId storyId
     * @return StoryContentBean
     */
    @Headers({Api.ZHIHU_DOMAIN})
    @GET("/api/4/news/{storyId}")
    Observable<StoryContentBean> getStoryContent(@Path("storyId") int storyId);

    /**
     * 获取相应文章的额外信息，如评论数量，获得的赞等
     *
     * @param storyId storyId
     * @return StoryExtraBean
     */
    @Headers({Api.ZHIHU_DOMAIN})
    @GET("/api/4/story-extra/{storyId}")
    Observable<StoryExtraBean> getStoryExtra(@Path("storyId") int storyId);

    /**
     * 获取文章长评论
     *
     * @param storyId storyId
     * @return List<CommentsBean>
     */
    @Headers({Api.ZHIHU_DOMAIN})
    @GET("/api/4/story/{storyId}/long-comments")
    Observable<List<CommentsBean>> getLongComments(@Path("storyId") int storyId);

    /**
     * 获取文章短评论
     *
     * @param storyId storyId
     * @return List<CommentsBean>
     */
    @Headers({Api.ZHIHU_DOMAIN})
    @GET("/api/4/story/{storyId}/short-comments")
    Observable<List<CommentsBean>> getShortComments(@Path("storyId") int storyId);

}
