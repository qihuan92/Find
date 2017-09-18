package com.qihuan.find.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.qihuan.find.common.AppConfig;
import com.qihuan.find.model.bean.find.CollectionBean;
import com.qihuan.find.model.db.dao.CollectionDao;

/**
 * AppDatabase
 * Created by Qi on 2017/8/24.
 */
@Database(entities = {CollectionBean.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, AppConfig.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CollectionDao collectionDao();
}
