package com.qihuan.find.net.api;

import com.qihuan.find.bean.zhihu.CommentsEntity;
import com.qihuan.find.bean.zhihu.DailyEntity;
import com.qihuan.find.bean.zhihu.StoryContentEntity;
import com.qihuan.find.bean.zhihu.StoryExtraEntity;
import com.qihuan.find.bean.zhihu.ThemeContentListEntity;
import com.qihuan.find.bean.zhihu.ThemesEntity;

import java.util.List;

import io.reactivex.Observable;
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
    Observable<DailyEntity> getLatestDaily();

    /**
     * 获取以前的文章列表
     *
     * @return
     */
    @GET("news/before/{date}")
    Observable<DailyEntity> getBeforeDaily(@Path("date") String date);

    /**
     * 获取相应文章内容
     *
     * @param storyId
     * @return
     */
    @GET("news/{storyId}")
    Observable<StoryContentEntity> getStoryContent(@Path("storyId") int storyId);

    /**
     * 获取相应文章的额外信息，如评论数量，获得的赞等
     *
     * @param storyId
     * @return
     */
    @GET("story-extra/{storyId}")
    Observable<StoryExtraEntity> getStoryExtra(@Path("storyId") int storyId);

    /**
     * 获取文章长评论
     *
     * @param storyId
     */
    @GET("story/{storyId}/long-comments")
    Observable<List<CommentsEntity>> getLongComments(@Path("storyId") int storyId);

    /**
     * 获取文章短评论
     *
     * @param storyId
     */
    @GET("story/{storyId}/short-comments")
    Observable<List<CommentsEntity>> getShortComments(@Path("storyId") int storyId);

    /**
     * 获取主题日报列表theme
     */
    @GET("themes")
    Observable<ThemesEntity> getThemes();

    /**
     * 获取主题日报内容列表
     *
     * @param themeId 主题日报id
     */
    @GET("theme/{themeId}")
    Observable<ThemeContentListEntity> getThemeContentList(@Path("themeId") int themeId);

}
