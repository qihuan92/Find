package com.qihuan.dailymodule.model;

import com.qihuan.dailymodule.model.bean.CommentsBean;
import com.qihuan.dailymodule.model.bean.DailyBean;
import com.qihuan.dailymodule.model.bean.StoryContentBean;
import com.qihuan.dailymodule.model.bean.StoryExtraBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 知乎日报
 * Created by Qi on 2017/6/20.
 */

public interface ZhihuApi {

    String ZHIHU_URL = "http://news-at.zhihu.com/api/4/";

    /**
     * 获取最新文章列表
     *
     * @return DailyBean
     */
    @GET("news/latest")
    Flowable<DailyBean> getLatestDaily();

    /**
     * 获取以前的文章列表
     *
     * @return DailyBean
     */
    @GET("news/before/{date}")
    Flowable<DailyBean> getBeforeDaily(@Path("date") String date);

    /**
     * 获取相应文章内容
     *
     * @param storyId storyId
     * @return StoryContentBean
     */
    @GET("news/{storyId}")
    Flowable<StoryContentBean> getStoryContent(@Path("storyId") int storyId);

    /**
     * 获取相应文章的额外信息，如评论数量，获得的赞等
     *
     * @param storyId storyId
     * @return StoryExtraBean
     */
    @GET("story-extra/{storyId}")
    Flowable<StoryExtraBean> getStoryExtra(@Path("storyId") int storyId);

    /**
     * 获取文章长评论
     *
     * @param storyId storyId
     * @return List<CommentsBean>
     */
    @GET("story/{storyId}/long-comments")
    Flowable<List<CommentsBean>> getLongComments(@Path("storyId") int storyId);

    /**
     * 获取文章短评论
     *
     * @param storyId storyId
     * @return List<CommentsBean>
     */
    @GET("story/{storyId}/short-comments")
    Flowable<List<CommentsBean>> getShortComments(@Path("storyId") int storyId);

}
