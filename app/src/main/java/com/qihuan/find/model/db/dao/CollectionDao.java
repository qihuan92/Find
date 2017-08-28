package com.qihuan.find.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qihuan.find.model.bean.find.CollectionBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * CollectionDao
 * Created by Qi on 2017/8/24.
 */
@Dao
public interface CollectionDao {

    @Query("SELECT * FROM t_collection")
    Flowable<List<CollectionBean>> selectAll();

    @Query("SELECT * FROM t_collection WHERE id IN (:collectionIds)")
    Flowable<List<CollectionBean>> selectAllByIds(int[] collectionIds);

    @Query("SELECT * FROM t_collection WHERE id = (:collectionId)")
    Flowable<CollectionBean> selectById(int collectionId);

    @Query("SELECT * FROM t_collection WHERE title = (:title)")
    Flowable<CollectionBean> selectByTitle(String title);

    @Insert
    void insert(CollectionBean collectionBeans);

    @Delete
    void delete(CollectionBean collectionBean);

    @Update
    void update(CollectionBean collectionBean);
}
