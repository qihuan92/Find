package com.qihuan.find.net.api;

import com.qihuan.find.bean.zhihu.BeforeDailyEntity;
import com.qihuan.find.bean.zhihu.LatestDailyEntity;
import com.qihuan.find.bean.zhihu.LongCommentsEntity;
import com.qihuan.find.bean.zhihu.ShortCommentsEntity;
import com.qihuan.find.bean.zhihu.SplashImgEntity;
import com.qihuan.find.bean.zhihu.StoryContentEntity;
import com.qihuan.find.bean.zhihu.StoryExtraEntity;
import com.qihuan.find.bean.zhihu.ThemeContentListEntity;
import com.qihuan.find.bean.zhihu.ThemesEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 知乎日报
 * Created by Qi on 2017/6/20.
 */

public interface ZhihuApi {
    /**
     * 获取启动界面图像
     *
     * @return
     */
    @GET("start-image/1080*1776")
    Observable<SplashImgEntity> getSplashImg();

    /**
     * 获取android最新版本
     *
     * @return
     */
    @GET("version/android/{versionCode}")
    Observable<SplashImgEntity> getLatestVersion(@Path("versionCode") String versionCode);

    /**
     * 获取最新文章列表
     *
     * @return
     */
    @GET("news/latest")
    Observable<LatestDailyEntity> getLatestDaily();

    /**
     * 获取以前的文章列表
     *
     * @return
     */
    @GET("news/before/{date}")
    Observable<BeforeDailyEntity> getBeforeDaily(@Path("date") String date);

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
    Observable<LongCommentsEntity> getLongComments(@Path("storyId") int storyId);

    /**
     * 获取文章短评论
     *
     * @param storyId
     */
    @GET("story/{storyId}/short-comments")
    Observable<ShortCommentsEntity> getShortComments(@Path("storyId") int storyId);

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
