package com.qihuan.find.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.qihuan.find.config.AppConfig;
import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.db.dao.StoryDao;

/**
 * DatabaseCreator
 * Created by Qi on 2017/8/24.
 */
@Database(entities = {StoryContentBean.class}, version = 1)
public abstract class DatabaseCreator extends RoomDatabase {
    private static DatabaseCreator sInstance;

    public synchronized static DatabaseCreator getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DatabaseCreator.class) {
                sInstance = Room.databaseBuilder(context, DatabaseCreator.class, AppConfig.DATABASE_NAME).build();
            }
        }
        return sInstance;
    }

    public abstract StoryDao storyDao();
}
