package com.qihuan.find.model.net.api;

import com.qihuan.find.model.bean.zhihu.CommentsBean;
import com.qihuan.find.model.bean.zhihu.DailyBean;
import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 知乎日报
 * Created by Qi on 2017/6/20.
 */

public interface ZhihuApi {

    /**
     * 获取最新文章列表
     *
     * @return
     */
    @GET("news/latest")
    Flowable<DailyBean> getLatestDaily();

    /**
     * 获取以前的文章列表
     *
     * @return
     */
    @GET("news/before/{date}")
    Flowable<DailyBean> getBeforeDaily(@Path("date") String date);

    /**
     * 获取相应文章内容
     *
     * @param storyId
     * @return
     */
    @GET("news/{storyId}")
    Flowable<StoryContentBean> getStoryContent(@Path("storyId") int storyId);

    /**
     * 获取相应文章的额外信息，如评论数量，获得的赞等
     *
     * @param storyId
     * @return
     */
    @GET("story-extra/{storyId}")
    Flowable<StoryExtraBean> getStoryExtra(@Path("storyId") int storyId);

    /**
     * 获取文章长评论
     *
     * @param storyId
     */
    @GET("story/{storyId}/long-comments")
    Flowable<List<CommentsBean>> getLongComments(@Path("storyId") int storyId);

    /**
     * 获取文章短评论
     *
     * @param storyId
     */
    @GET("story/{storyId}/short-comments")
    Flowable<List<CommentsBean>> getShortComments(@Path("storyId") int storyId);

}
