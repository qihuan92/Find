package com.qihuan.commonmodule.collection

import androidx.room.*
import io.reactivex.Single

/**
 * CollectionDao
 * @author qi
 * @date 2018/10/17
 */
@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(collectionBean: CollectionBean): Single<Long>

    @Delete
    fun delete(collectionBean: CollectionBean): Single<Int>

    @Query("select * from collection where id = :id and type = :type")
    fun queryOne(id: String, type: Int = 0): Single<CollectionBean>

    @Query("select * from collection")
    fun queryAll(): Single<List<CollectionBean>>
}