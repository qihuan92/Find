package com.qihuan.commonmodule.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.qihuan.commonmodule.base.BaseApp
import com.qihuan.commonmodule.collection.CollectionBean
import com.qihuan.commonmodule.collection.CollectionDao


/**
 * AppDatabase
 * @author qi
 * @date 2018/10/17
 */
@Database(entities = [CollectionBean::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun collectionDao(): CollectionDao

    companion object {
        private const val APP_DATABASE_NAME = "find.db"
        val instance: AppDatabase by lazy {
            Room.databaseBuilder(BaseApp.instance, AppDatabase::class.java, APP_DATABASE_NAME).build()
        }
    }
}