package com.qihuan.find.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qihuan.find.model.bean.zhihu.StoryContentBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * StoryDao
 * Created by Qi on 2017/8/24.
 */
@Dao
public interface StoryDao {

    @Query("SELECT * FROM t_story")
    Flowable<List<StoryContentBean>> selectAll();

    @Query("SELECT * FROM t_story WHERE id IN (:storyIds)")
    Flowable<List<StoryContentBean>> selectAllByIds(int[] storyIds);

    @Query("SELECT * FROM t_story WHERE title = (:id)")
    Flowable<StoryContentBean> selectById(int id);

    @Query("SELECT * FROM t_story WHERE title = (:title)")
    Flowable<StoryContentBean> selectByTitle(String title);

    @Insert
    void insertAll(StoryContentBean... storyContentBeans);

    @Delete
    void delete(StoryContentBean storyContentBean);
}
